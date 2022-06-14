package com.example.e_invoicingpaymentsystem.mapper;

import com.example.e_invoicingpaymentsystem.dto.ImportedXmlDto;
import com.example.e_invoicingpaymentsystem.model.Debt;
import com.example.e_invoicingpaymentsystem.model.Invoice;
import com.example.e_invoicingpaymentsystem.model.enums.PaymentStatus;
import com.example.e_invoicingpaymentsystem.repository.CompanyRepository;
import com.example.e_invoicingpaymentsystem.repository.DebtRepository;
import com.example.e_invoicingpaymentsystem.repository.InvoiceRepository;
import com.example.e_invoicingpaymentsystem.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FromImportedXmlDtoToInvoice {

    InvoiceRepository invoiceRepository;
    SupplierRepository supplierRepository;
    CompanyRepository companyRepository;

    DebtRepository debtRepository;

    @Autowired
    public FromImportedXmlDtoToInvoice(InvoiceRepository invoiceRepository,
                                       SupplierRepository supplierRepository,
                                       CompanyRepository companyRepository,
                                       DebtRepository debtRepository
    ) {
        this.invoiceRepository = invoiceRepository;
        this.supplierRepository = supplierRepository;
        this.companyRepository = companyRepository;
        this.debtRepository = debtRepository;
    }

    public Invoice fromImportedXmlDtoToInvoice(ImportedXmlDto importedXmlDto) {
        String invoiceNumber = importedXmlDto.getInvoiceSeries() + importedXmlDto.getInvoiceNumber();
        if (!invoiceRepository.existsInvoiceByInvoiceNumber(invoiceNumber)) {
            Invoice invoice = new Invoice();
            invoice.setInvoiceNumber(invoiceNumber);
            invoice.setDeliveryDate(importedXmlDto.getDeliveryDate());
            invoice.setSuppAccountNumber(importedXmlDto.getSuppAccountNumber());
            invoice.setTotalPrice(importedXmlDto.getTotalPrice());
            invoice.setSubmissionDate(importedXmlDto.getSubmissionDate());
            invoice.setPaymentStatus(PaymentStatus.UNPAID);
            invoice.setInvoiceDebt(importedXmlDto.getTotalPrice());
            invoice.setCompany(companyRepository.findByTin(importedXmlDto.getBuyerTin()));
            invoice.setSupplier(supplierRepository.getSupplierBySupplierTin(importedXmlDto.getSupplierTin()));
            invoiceRepository.save(invoice);
            if (!debtRepository.existsDebtByCompanyAndSupplier(
                    invoice.getCompany(), invoice.getSupplier())) {
                Debt debt = new Debt();
                debt.setCompany(invoice.getCompany());
                debt.setSupplier(invoice.getSupplier());
                debt.setTotal_debt(invoice.getTotalPrice());
                debtRepository.save(debt);
            }
            Debt debt = debtRepository.
                    findDebtByCompanyAndSupplier(invoice.getCompany(), invoice.getSupplier());
            debt.setTotal_debt(debt.getTotal_debt() + invoice.getTotalPrice());
            debtRepository.save(debt);
        }
        return invoiceRepository.getInvoiceByInvoiceNumber(invoiceNumber);
    }


}
