package com.example.e_invoicingpaymentsystem.config.security;

import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CompanyRepository companyRepository;

    @Autowired
    public UserDetailsServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String tin) throws UsernameNotFoundException {
        Company company = companyRepository.findByTin(tin);
        if (company == null) {
            throw new UsernameNotFoundException("User with username" + tin + " is not found");
        }
        return new CurrentUser(company);
    }
}
