package com.example.e_invoicingpaymentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @Column(name = "supplier_name", nullable = false, unique = true)
    private String supplierName;

    @Column(name ="supp_account_number", nullable = false)
    private String suppAccountNumber;

    @Column (name = "supplier_tin", nullable = false, unique = true)
    private String supplierTin;

    @OneToMany (mappedBy = "supplier", cascade = CascadeType.PERSIST)
    List<Invoice> invoices = new ArrayList<>();

    @OneToMany (mappedBy = "supplier", cascade = CascadeType.PERSIST)
    List<Debt> debts = new ArrayList<>();



}
