package com.example.e_invoicingpaymentsystem.validator;

import com.example.e_invoicingpaymentsystem.dto.SignUpDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyValidator {
    public boolean isValidCompany(SignUpDto signUpDto) {
        return (!(signUpDto.getTin() == null ||
                signUpDto.getCompanyName() == null ||
                signUpDto.getCompAccountNumber() == null ||
                signUpDto.getEmail() == null ||
                signUpDto.getPhoneNumber() == null ||
                signUpDto.getPassword() == null));
    }

    public boolean isValidCompanyDto(SignUpDto signUpDto) {
        return (!(signUpDto.getCompanyName() == null ||
                signUpDto.getCompAccountNumber() == null ||
                signUpDto.getEmail() == null ||
                signUpDto.getPhoneNumber() == null ||
                signUpDto.getPassword() == null));
    }
}
