package com.example.e_invoicingpaymentsystem.controller;

import com.example.e_invoicingpaymentsystem.dto.CompanyDto;
import com.example.e_invoicingpaymentsystem.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/importinvoice")
    public ResponseEntity<?> createCompany(@RequestParam String path) throws Exception {
        return invoiceService.importInvoice(path);
    }
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/allinvoices")
    public ResponseEntity<?> findAllInvoices( @RequestParam int page,
                                              @RequestParam int perPage){
        return invoiceService.findAllInvoices(page,perPage);

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
