package com.example.e_invoicingpaymentsystem.config.security;

import com.example.e_invoicingpaymentsystem.model.Company;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private final Company company;

    public CurrentUser(Company company) {
        super(company.getTin(), company.getPassword(), AuthorityUtils.createAuthorityList(String.valueOf(company.getRole())));
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }
}
