package com.example.e_invoicingpaymentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "debt")

public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debt_id_generator")
    @SequenceGenerator(name = "debt_id_generator", sequenceName = "debt_id_seq", allocationSize = 30)
    private Long debtId;

    @Column(name = "total_debt")
    private Double total_debt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "fk_supCompDept_company_manytoone"))
    Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "fk_supCompDept_supplier_manytoone"))
    Supplier supplier;
}
