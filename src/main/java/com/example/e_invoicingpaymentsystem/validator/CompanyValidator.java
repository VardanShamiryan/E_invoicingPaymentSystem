package com.example.e_invoicingpaymentsystem.validator;

import com.example.e_invoicingpaymentsystem.dto.CompanyDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyValidator {
    public boolean isValidCompany(CompanyDto companyDto) {

        return (!(companyDto.getTin() == null ||
                companyDto.getCompanyName() == null ||
                companyDto.getCompAccountNumber() == null ||
                companyDto.getEmail() == null ||
                companyDto.getPhoneNumber() == null ||
                companyDto.getPassword() == null
        )
        );
    }

    public boolean isValidCompanyDto(CompanyDto companyDto) {
        return (!(companyDto.getCompanyName() == null ||
                companyDto.getCompAccountNumber() == null ||
                companyDto.getEmail() == null ||
                companyDto.getPhoneNumber() == null ||
                companyDto.getPassword() == null
        )
        );
    }
}