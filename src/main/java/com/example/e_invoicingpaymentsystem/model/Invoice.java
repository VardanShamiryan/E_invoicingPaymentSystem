package com.example.e_invoicingpaymentsystem.model;

import com.example.e_invoicingpaymentsystem.model.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_id_generator")
    @SequenceGenerator(name = "invoice_id_generator", sequenceName = "invoice_id_seq", allocationSize = 30)
    private Long invoiceId;

    @Column(name = "invoice_number", unique = true, nullable = false)
    private String invoiceNumber;

    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @Column(name = "supp_account_number", nullable = false)
    private String suppAccountNumber;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "submission_date", nullable = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate submissionDate;

    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @Column(name = "invoice_debt")
    private Double invoiceDebt;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.PERSIST)
    private List<PaymentOrder> paymentOrders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "fk_invoice_company_manyToOne"))
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "fk_invoice_supplier_manyToOne"))
    private Supplier supplier;

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", suppAccountNumber='" + suppAccountNumber + '\'' +
                ", totalPrice=" + totalPrice +
                ", submissionDate=" + submissionDate +
                ", paymentStatus=" + paymentStatus +
                ", invoiceDebt=" + invoiceDebt +
                "}\n";
    }
}
