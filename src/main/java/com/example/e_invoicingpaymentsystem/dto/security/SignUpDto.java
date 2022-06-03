package com.example.e_invoicingpaymentsystem.dto.security;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private String tin;
    private String password;
    private String companyName;
    private String email;
    private String compAccountNumber;
    private String phoneNumber;
}
