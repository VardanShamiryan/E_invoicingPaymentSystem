package com.example.e_invoicingpaymentsystem.repository;

import com.example.e_invoicingpaymentsystem.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    boolean existsInvoiceByInvoiceNumber(String invoiceNumber);

    Invoice getInvoiceByInvoiceNumber(String invoiceNumber);

    List<Invoice> findInvoicesByPaymentStatus(PaymentStatus paymentStatus);
boolean existsInvoiceByInvoiceNumber(String invoiceNumber);
Invoice getInvoiceByInvoiceNumber(String invoiceNumber);


 /*  @ PersistenceContext
    EntityManager entityManager=null;

    public default List<Invoice> findAllInvoices(int limit) {
        return entityManager.createQuery("SELECT p FROM Invoice p ORDER BY p.invoiceNumber",
                Invoice.class).setMaxResults(limit).getResultList();
    }

*/

   /* @Query("select i from Invoice i ORDER BY  i.invoiceNumber LIMIT  10 ")

    default List<Invoice> findAll(int offset){
          final int perPage=10;

    }*/
    @Query("SELECT s FROM Invoice s ORDER BY s.invoiceNumber DESC")
    List<Invoice> findAllInvoices(PageRequest pageable);
}
