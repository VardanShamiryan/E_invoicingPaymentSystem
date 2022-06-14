package com.example.e_invoicingpaymentsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderDto {
    private Double paymentAmount;
    private final LocalDate currentPaymentDate = LocalDate.now();
    @JsonProperty("invoice")
    private InvoiceDto invoiceDto;
}
