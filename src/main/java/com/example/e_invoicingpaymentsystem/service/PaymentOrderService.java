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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentOrderService {
    private InvoiceRepository invoiceRepository;
    private DebtRepository debtRepository;
    private PaymentOrderRepository paymentOrderRepository;

    public PaymentOrderService(InvoiceRepository invoiceRepository,
                               DebtRepository debtRepository,
                               PaymentOrderRepository paymentOrderRepository) {
        this.invoiceRepository = invoiceRepository;
        this.debtRepository = debtRepository;
        this.paymentOrderRepository = paymentOrderRepository;
    }

    public ResponseEntity<?> paymentOrder(Double amount, String invoiceNumber) {
        if (!invoiceRepository.existsInvoiceByInvoiceNumber(invoiceNumber)) {
            return new ResponseEntity<>("No invoice with such invoice number!", HttpStatus.BAD_REQUEST);
        }
        if (amount <= 0) {
            return new ResponseEntity<>("Insert valid amount!", HttpStatus.BAD_REQUEST);
        }
        Invoice invoice = invoiceRepository.getInvoiceByInvoiceNumber(invoiceNumber);
        if (amount > invoice.getInvoiceDebt()) {
            return new ResponseEntity<>("Your invoice debt is " + invoice.getInvoiceDebt() + "" +
                    "! Please pay less or equal then.", HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (!currentPrincipalName.equals(invoice.getCompany().getTin())) {
            return new ResponseEntity<>("You can pay only for your company!", HttpStatus.BAD_REQUEST);
        }
        Debt debt = debtRepository.findDebtByCompanyAndSupplier(invoice.getCompany(), invoice.getSupplier());
        String fromAccountNumber = invoice.getCompany().getCompAccountNumber();
        String toAccountNumber = invoice.getSuppAccountNumber();
        Order order = new Order(amount, fromAccountNumber, toAccountNumber);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(order);

        //String url = "http://192.168.10.83:8080/api/account/transferAccountAccount";
        String url = "http://localhost:8080/api/account/transferAccountAccount";

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        try {
            ResponseEntity<?> response = template.exchange(url, HttpMethod.PUT, entity, String.class);

            if (response.getStatusCode().value() == 200) {
                invoice.setInvoiceDebt(invoice.getInvoiceDebt() - amount);
                if (invoice.getInvoiceDebt() == 0) {
                    invoice.setPaymentStatus(PaymentStatus.PAID);
                } else {
                    invoice.setPaymentStatus(PaymentStatus.PARTIALLY_PAID);
                }
                invoiceRepository.save(invoice);
                debt.setTotalDebt(debt.getTotalDebt() - amount);
                debtRepository.save(debt);
                PaymentOrder paymentOrder = new PaymentOrder();
                paymentOrder.setPaymentAmount(amount);
                paymentOrder.setInvoice(invoice);
                paymentOrderRepository.save(paymentOrder);
            }
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }


}
