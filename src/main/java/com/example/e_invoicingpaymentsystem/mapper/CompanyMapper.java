package com.example.e_invoicingpaymentsystem.mapper;

import com.example.e_invoicingpaymentsystem.dto.SignUpDto;
import com.example.e_invoicingpaymentsystem.model.Company;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class CompanyMapper {
    private final BCryptPasswordEncoder passwordEncoder;

    public CompanyMapper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
//    public CompanyMapper(BCryptPasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }


    //    private final PasswordEncoder passwordEncoder;
//
//    public CompanyMapper(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public Company toCompanyFromSignUpDto(SignUpDto signUpDto) {
//        Company company = new Company();
//        company.setTin(signUpDto.getTin());
//        company.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
//        company.setCompanyName(signUpDto.getCompanyName());
//        company.setEmail(signUpDto.getEmail());
//        company.setCompAccountNumber(signUpDto.getCompAccountNumber());
//        company.setPhoneNumber(signUpDto.getPhoneNumber());
//        return company;
//    }


    public SignUpDto toCompanyDto(Company company) {

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setTin(company.getTin());
        signUpDto.setCompanyName(company.getCompanyName());
        signUpDto.setCompAccountNumber(company.getCompAccountNumber());
        signUpDto.setEmail(company.getEmail());
        signUpDto.setPhoneNumber(company.getPhoneNumber());
        signUpDto.setPassword(company.getPassword());

        return signUpDto;
    }

    public Company toCompany(SignUpDto signUpDto) {

        Company company = new Company();
        company.setTin(signUpDto.getTin());
        company.setCompanyName(signUpDto.getCompanyName());
        company.setCompAccountNumber(signUpDto.getCompAccountNumber());
        company.setEmail(signUpDto.getEmail());
        company.setPhoneNumber(signUpDto.getPhoneNumber());
        company.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        //company.setPassword(signUpDto.getPassword());
        return company;
    }



}