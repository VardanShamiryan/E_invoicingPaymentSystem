package com.example.e_invoicingpaymentsystem.repository;

import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.model.Invoice;
import com.example.e_invoicingpaymentsystem.model.enums.PaymentStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    boolean existsInvoiceByInvoiceNumber(String invoiceNumber);

    boolean existsInvoiceByAdjustmentInvoiceNumber(String parentInvoiceNumber);

    Invoice getInvoiceByInvoiceNumber(String invoiceNumber);

    List<Invoice> findInvoicesByPaymentStatusAndCompany(PaymentStatus paymentStatus, Company company);



    @Query("SELECT s FROM Invoice s ORDER BY s.invoiceNumber DESC")
    List<Invoice> findAllInvoicesByCompany(PageRequest pageable, Company company);
}
