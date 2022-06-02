package com.example.e_invoicingpaymentsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String companyName;
    private String compAccountNumber;
    private String email;
    private String phoneNumber;
}
