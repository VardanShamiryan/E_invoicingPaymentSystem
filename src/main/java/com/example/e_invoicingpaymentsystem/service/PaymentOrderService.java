package com.example.e_invoicingpaymentsystem.service;

import com.example.e_invoicingpaymentsystem.dto.Order;
import com.example.e_invoicingpaymentsystem.model.Debt;
import com.example.e_invoicingpaymentsystem.model.Invoice;
import com.example.e_invoicingpaymentsystem.model.PaymentOrder;
import com.example.e_invoicingpaymentsystem.model.enums.PaymentStatus;
import com.example.e_invoicingpaymentsystem.repository.DebtRepository;
import com.example.e_invoicingpaymentsystem.repository.InvoiceRepository;
import com.example.e_invoicingpaymentsystem.repository.PaymentOrderRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentOrderService {
    final InvoiceRepository invoiceRepository;
    final DebtRepository debtRepository;
    final PaymentOrderRepository paymentOrderRepository;

    public PaymentOrderService(InvoiceRepository invoiceRepository,
                               DebtRepository debtRepository,
                               PaymentOrderRepository paymentOrderRepository) {
        this.invoiceRepository = invoiceRepository;
        this.debtRepository = debtRepository;
        this.paymentOrderRepository = paymentOrderRepository;
    }

    public ResponseEntity<?> paymentOrder(Double amount, String invoiceNumber) {
        Invoice invoice = invoiceRepository.getInvoiceByInvoiceNumber(invoiceNumber);
        Debt debt = debtRepository.findDebtByCompanyAndSupplier(invoice.getCompany(), invoice.getSupplier());
        String fromAccountNumber = invoice.getCompany().getCompAccountNumber();
        String toAccountNumber = invoice.getSuppAccountNumber();
        Order order = new Order(amount, fromAccountNumber, toAccountNumber);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(order);

        String url = "http://192.168.10.83:8080/api/account/transferAccountAccount";
        //String url = "http://localhost:8080/api/account/transferAccountAccount";

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<?> response = template.exchange(url, HttpMethod.PUT, entity, String.class);

        if (response.getStatusCode().value() == 200) {
            invoice.setInvoiceDebt(invoice.getInvoiceDebt() - amount);
            if (invoice.getInvoiceDebt() == 0) {
                invoice.setPaymentStatus(PaymentStatus.PAID);
            } else {
                invoice.setPaymentStatus(PaymentStatus.PARTIALLY_PAID);
            }
            invoiceRepository.save(invoice);
            debt.setTotal_debt(debt.getTotal_debt() - amount);
            debtRepository.save(debt);
            PaymentOrder paymentOrder = new PaymentOrder();
            paymentOrder.setPaymentAmount(amount);
            paymentOrder.setInvoice(invoice);
            paymentOrderRepository.save(paymentOrder);
        }
        return response;
    }
}
