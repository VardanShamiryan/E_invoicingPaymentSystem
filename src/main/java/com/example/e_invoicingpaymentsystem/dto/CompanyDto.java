package com.example.e_invoicingpaymentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private String tin;
    //private String password;
    private String companyName;
    private String compAccountNumber;
    private String email;
    private String phoneNumber;
}
