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
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compId;

    @Column(name = "tin", unique = true, nullable = false)
    private String tin;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "comp_account_number", nullable = false)
    private String compAccountNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "company", cascade = CascadeType.PERSIST)
    List<Invoice> invoices = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.PERSIST)
    List<Debt> debts = new ArrayList<>();



}
