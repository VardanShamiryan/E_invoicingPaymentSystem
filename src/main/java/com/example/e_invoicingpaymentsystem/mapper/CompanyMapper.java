package com.example.e_invoicingpaymentsystem.mapper;

import com.example.e_invoicingpaymentsystem.dto.security.SignUpDto;
import com.example.e_invoicingpaymentsystem.model.Company;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    private final PasswordEncoder passwordEncoder;

    public CompanyMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Company toCompanyFromSignUpDto(SignUpDto signUpDto) {
        Company company = new Company();
        company.setTin(signUpDto.getTin());
        company.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        company.setCompanyName(signUpDto.getCompanyName());
        company.setEmail(signUpDto.getEmail());
        company.setCompAccountNumber(signUpDto.getCompAccountNumber());
        company.setPhoneNumber(signUpDto.getPhoneNumber());
        return company;
    }

//    public CompanyDto toCompanyDto(Company company) {
//        CompanyDto companyDto = new CompanyDto();
//        bankDto.setBankCode(bank.getBankCode());
//        bankDto.setBankName(bank.getBankName());
//        bankDto.setBankType(bank.getBankType());
//        return companyDto;
//    }

}
