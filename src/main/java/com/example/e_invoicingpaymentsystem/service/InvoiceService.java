package com.example.e_invoicingpaymentsystem.service;

import com.example.e_invoicingpaymentsystem.dto.ImportedXmlDto;
import com.example.e_invoicingpaymentsystem.fileReader.ImportFromXml;
import com.example.e_invoicingpaymentsystem.mapper.FromImportedXmlDtoToInvoice;
import com.example.e_invoicingpaymentsystem.mapper.FromImportedXmlDtoToSupplier;
import com.example.e_invoicingpaymentsystem.model.Invoice;
import com.example.e_invoicingpaymentsystem.model.enums.PaymentStatus;
import com.example.e_invoicingpaymentsystem.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.e_invoicingpaymentsystem.fileReader.ImportFromXml.importFromXml;

@Service
public class InvoiceService {
    ImportFromXml importFromXml;
    FromImportedXmlDtoToSupplier supplierMapper;
    FromImportedXmlDtoToInvoice invoiceMapper;
    InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(ImportFromXml importFromXml,
                          FromImportedXmlDtoToSupplier supplierMapper,
                          FromImportedXmlDtoToInvoice invoiceMapper,
                          InvoiceRepository invoiceRepository) {
        this.importFromXml = importFromXml;
        this.supplierMapper = supplierMapper;
        this.invoiceMapper = invoiceMapper;
        this.invoiceRepository = invoiceRepository;
    }

    public ResponseEntity<?> importInvoice(String path) {
        List<ImportedXmlDto> importedXmlDtoList;
        try {
            importedXmlDtoList = importFromXml(path);
        } catch (Exception e) {
            return new ResponseEntity<>("Path doesn't exist!", HttpStatus.BAD_REQUEST);
        }
        for (ImportedXmlDto importedXmlDto : importedXmlDtoList) {
            supplierMapper.fromImportedXmlDtoToSupplier(importedXmlDto);
            invoiceMapper.fromImportedXmlDtoToInvoice(importedXmlDto);
        }
        return new ResponseEntity<>("Invoices imported.", HttpStatus.OK);
    }

    public ResponseEntity<?> findPaidInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findInvoicesByPaymentStatus(PaymentStatus.PAID);
        return new ResponseEntity<>(invoiceList.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> findUnpaidInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findInvoicesByPaymentStatus(PaymentStatus.UNPAID);

        return new ResponseEntity<>(invoiceList.toString(), HttpStatus.OK);
    }
}
