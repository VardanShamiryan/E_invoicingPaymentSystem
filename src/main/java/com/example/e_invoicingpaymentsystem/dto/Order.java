package com.example.e_invoicingpaymentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Double amount;
    private String fromAccountNumber;
    private String toAccountNumber;
}
