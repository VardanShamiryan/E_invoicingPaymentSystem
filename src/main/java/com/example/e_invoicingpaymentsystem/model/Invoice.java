package com.example.e_invoicingpaymentsystem.model;

import com.example.e_invoicingpaymentsystem.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    @Column(name = "supp_account_number", nullable = false)
    private String suppAccountNumber;

    @Column(name = "invoice_series", nullable = false)
    private String invoiceSeries;

    @Column(name = "invoice_number", nullable = false)
    private String invoiceNumber;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @Column(name = "invoice_debt")
    private Double invoiceDebt;

    @Column(name = "submission_date", nullable = false)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD/MM/yyyy")
    private LocalDate submissionDate;

    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.PERSIST)
    private List<PaymentOrder> paymentOrders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "fk_invoice_company_manyToOne"))
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "fk_invoice_supplier_manyToOne"))
    private Supplier supplier;


}
