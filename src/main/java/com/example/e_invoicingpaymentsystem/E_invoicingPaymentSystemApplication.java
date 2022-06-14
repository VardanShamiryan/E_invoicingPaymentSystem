package com.example.e_invoicingpaymentsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@OpenAPIDefinition(
        info = @Info(
                title = "E-invoicing payment system",
                version = "1",
                description = "Payment system"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer")
public class E_invoicingPaymentSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(E_invoicingPaymentSystemApplication.class, args);
    }
}
