package com.example.e_invoicingpaymentsystem.service;

import com.example.e_invoicingpaymentsystem.dto.ImportedXmlDto;
import com.example.e_invoicingpaymentsystem.fileReader.ImportFromXml;
import com.example.e_invoicingpaymentsystem.mapper.FromImportedXmlDtoToInvoice;
import com.example.e_invoicingpaymentsystem.mapper.FromImportedXmlDtoToSupplier;
import com.example.e_invoicingpaymentsystem.mapper.InvoiceMapper;
import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.model.Invoice;
import com.example.e_invoicingpaymentsystem.model.enums.PaymentStatus;
import com.example.e_invoicingpaymentsystem.repository.CompanyRepository;
import com.example.e_invoicingpaymentsystem.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.e_invoicingpaymentsystem.fileReader.ImportFromXml.importFromXml;

@Service
public class InvoiceService {
    private ImportFromXml importFromXml;
    private FromImportedXmlDtoToSupplier supplierMapper;
    private FromImportedXmlDtoToInvoice invoiceMapper;
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapp;
    private CompanyRepository companyRepository;

    @Autowired
    public InvoiceService(ImportFromXml importFromXml,
                          FromImportedXmlDtoToSupplier supplierMapper,
                          FromImportedXmlDtoToInvoice invoiceMapper,
                          InvoiceRepository invoiceRepository,
                          InvoiceMapper invoiceMapp,
                          CompanyRepository companyRepository) {
        this.importFromXml = importFromXml;
        this.supplierMapper = supplierMapper;
        this.invoiceMapper = invoiceMapper;
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapp = invoiceMapp;
        this.companyRepository = companyRepository;
    }

    public ResponseEntity<?> importInvoice(String path) {
        List<ImportedXmlDto> importedXmlDtoList;
        try {
            importedXmlDtoList = importFromXml(path);
        } catch (Exception e) {
            return new ResponseEntity<>("Path doesn't exist!", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        for (ImportedXmlDto importedXmlDto : importedXmlDtoList) {
            if (!currentPrincipalName.equals(importedXmlDto.getBuyerTin())) {
                return new ResponseEntity<>("You can't import others invoices.",
                        HttpStatus.BAD_REQUEST);
            }
            supplierMapper.fromImportedXmlDtoToSupplier(importedXmlDto);
            invoiceMapper.fromImportedXmlDtoToInvoice(importedXmlDto);
        }
        return new ResponseEntity<>("Invoices imported.", HttpStatus.OK);
    }

    public ResponseEntity<?> findAllInvoices(int page, int perPage) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Company company = companyRepository.findByTin(currentPrincipalName);
        List<Invoice> invoices = invoiceRepository.findAllInvoicesByCompany(PageRequest.of(page, perPage),
                company);
        return new ResponseEntity<>(invoiceMapp.toInvoiceDto(invoices), HttpStatus.OK);
    }

    public ResponseEntity<?> findPaidInvoices() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Company company = companyRepository.findByTin(currentPrincipalName);
        List<Invoice> invoiceList = invoiceRepository
                .findInvoicesByPaymentStatusAndCompany(PaymentStatus.PAID, company);
        return new ResponseEntity<>(invoiceMapp.toInvoiceDto(invoiceList), HttpStatus.OK);
    }

    public ResponseEntity<?> findUnpaidAndPartiallyPaidInvoices() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Company company = companyRepository.findByTin(currentPrincipalName);
        List<Invoice> unpaidInvoices = invoiceRepository.findInvoicesByPaymentStatusAndCompany(
                PaymentStatus.UNPAID, company);
        List<Invoice> partiallyPaidInvoices = invoiceRepository.findInvoicesByPaymentStatusAndCompany(
                PaymentStatus.PARTIALLY_PAID, company);
        unpaidInvoices.addAll(partiallyPaidInvoices);
        return new ResponseEntity<>(invoiceMapp.toInvoiceDto(unpaidInvoices), HttpStatus.OK);
    }
}
