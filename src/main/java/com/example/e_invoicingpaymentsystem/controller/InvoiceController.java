package com.example.e_invoicingpaymentsystem.controller;

import com.example.e_invoicingpaymentsystem.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/importinvoices")
    public ResponseEntity<?> importInvoices(@RequestParam String path) throws Exception {
        return invoiceService.importInvoice(path);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public ResponseEntity<?> findAllInvoices(@RequestParam int page, @RequestParam int perPage) {
        return invoiceService.findAllInvoices(page, perPage);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/paid")
    public ResponseEntity<?> findPayedInvoices() {
        return invoiceService.findPaidInvoices();
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/unpaid")
    public ResponseEntity<?> findUnpaidAndPartiallyPaidInvoices() {
        return invoiceService.findUnpaidAndPartiallyPaidInvoices();
    }
}
