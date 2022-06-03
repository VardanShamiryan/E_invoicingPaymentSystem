package com.example.e_invoicingpaymentsystem.dto.security;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogInDto {
    private String tin;
    private String password;
}
