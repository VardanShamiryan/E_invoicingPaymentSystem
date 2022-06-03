package com.example.e_invoicingpaymentsystem.service.security;

import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.model.Role;
import com.example.e_invoicingpaymentsystem.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomCompanyDetailsService implements UserDetailsService {

    private CompanyRepository companyRepository;

    @Autowired
    public CustomCompanyDetailsService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String tin) throws UsernameNotFoundException {
        Company company;
        try {
            company = companyRepository.findByTin(tin);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Company not found with this tin:" + tin);
        }
        return new User(company.getTin(),
                company.getPassword(), mapRolesToAuthorities(company.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).
                collect(Collectors.toList());
    }
}