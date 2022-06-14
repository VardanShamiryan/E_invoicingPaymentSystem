package com.example.e_invoicingpaymentsystem.repository;

import com.example.e_invoicingpaymentsystem.model.Invoice;
import com.example.e_invoicingpaymentsystem.model.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    boolean existsInvoiceByInvoiceNumber(String invoiceNumber);

    Invoice getInvoiceByInvoiceNumber(String invoiceNumber);

    List<Invoice> findInvoicesByPaymentStatus(PaymentStatus paymentStatus);
}
