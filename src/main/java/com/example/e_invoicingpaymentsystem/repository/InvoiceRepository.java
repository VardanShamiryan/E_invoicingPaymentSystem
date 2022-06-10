package com.example.e_invoicingpaymentsystem.repository;

import com.example.e_invoicingpaymentsystem.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
boolean existsInvoiceByInvoiceNumber(String invoiceNumber);
Invoice getInvoiceByInvoiceNumber(String invoiceNumber);
}
