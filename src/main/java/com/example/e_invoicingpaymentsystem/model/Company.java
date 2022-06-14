package com.example.e_invoicingpaymentsystem.model;

import com.example.e_invoicingpaymentsystem.model.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_generator")
    @SequenceGenerator(name = "company_id_generator", sequenceName = "company_id_seq", allocationSize = 30)
    @Column(name = "comp_id")
    private Long id;

//    @Pattern(regexp = "[0-9]", message = "Tin must contain only digits")
    @Column(name = "tin", unique = true, nullable = false)
    private String tin;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    //@Pattern(regexp = "[0-9]{16}", message = "account number must contain only digits and be 16 characters long")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    Roles role=Roles.ROLE_USER;

//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "comp_id", referencedColumnName = "comp_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
//    private Set<Role> roles;

}
