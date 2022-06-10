package com.example.e_invoicingpaymentsystem.mapper;

import com.example.e_invoicingpaymentsystem.dto.CompanyDto;
import com.example.e_invoicingpaymentsystem.model.Company;
import org.springframework.stereotype.Component;


@Component
public class CompanyMapper {
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
    private static final AES aes = new AES();

    public String encryptPassword(String password) {
        String encryptedMessage = null;
        try {
            aes.init();
            encryptedMessage = aes.encrypt(password);
        } catch (Exception ignored) {
        }
        return encryptedMessage;
    }

    public CompanyDto toCompanyDto(Company company) {

        CompanyDto companyDto = new CompanyDto();
        companyDto.setTin(company.getTin());
        companyDto.setCompanyName(company.getCompanyName());
        companyDto.setCompAccountNumber(company.getCompAccountNumber());
        companyDto.setEmail(company.getEmail());
        companyDto.setPhoneNumber(company.getPhoneNumber());
        companyDto.setPassword(company.getPassword());

        return companyDto;
    }

    public Company toCompany(CompanyDto companyDto) {

        Company company = new Company();
        company.setTin(companyDto.getTin());
        company.setCompanyName(companyDto.getCompanyName());
        company.setCompAccountNumber(companyDto.getCompAccountNumber());
        company.setEmail(companyDto.getEmail());
        company.setPhoneNumber(companyDto.getPhoneNumber());
        company.setPassword(encryptPassword(companyDto.getPassword()));
        return company;
    }

}