package com.example.e_invoicingpaymentsystem.mapper;

import com.example.e_invoicingpaymentsystem.dto.InvoiceDto;
import com.example.e_invoicingpaymentsystem.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {
    CompanyMapper companyMapper;
    SupplierMapper supplierMapper;

    @Autowired
    public InvoiceMapper(CompanyMapper companyMapper, SupplierMapper supplierMapper) {
        this.companyMapper = companyMapper;
        this.supplierMapper = supplierMapper;
    }

    public InvoiceDto toInvoiceDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceDto.setDeliveryDate(invoice.getDeliveryDate());
        invoiceDto.setSuppAccountNumber(invoice.getSuppAccountNumber());
        invoiceDto.setTotalPrice(invoice.getTotalPrice());
        invoiceDto.setSubmissionDate(invoice.getSubmissionDate());
        invoiceDto.setPaymentStatus(invoice.getPaymentStatus());
        invoiceDto.setInvoiceDebt(invoice.getInvoiceDebt());
        invoiceDto.setSignUpDto(companyMapper.toCompanyDto(invoice.getCompany()));
        invoiceDto.setSupplierDto(supplierMapper.toSupplierDto(invoice.getSupplier()));
        return invoiceDto;
    }

    public List<InvoiceDto> toInvoiceDto(List<Invoice> clients) {
        return clients.stream().map(this::toInvoiceDto).collect(Collectors.toList());
    }
}
