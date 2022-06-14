package com.example.e_invoicingpaymentsystem.controller;

import com.example.e_invoicingpaymentsystem.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @PostMapping("/importinvoice")
    public ResponseEntity<?> createCompany(@RequestParam String path) throws Exception {
        return invoiceService.importInvoice(path);
    }

    @GetMapping("/paid")
    public ResponseEntity<?> findPayedInvoices() {
        return invoiceService.findPaidInvoices();
    }

    @GetMapping("/unpaid")
    public ResponseEntity<?> findUnpaidInvoices() {
        return invoiceService.findUnpaidInvoices();
    }
}
