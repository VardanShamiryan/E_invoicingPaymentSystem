package com.example.e_invoicingpaymentsystem.dto;

import com.example.e_invoicingpaymentsystem.model.enums.PaymentStatus;
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
public class InvoiceDto {

    private String suppAccountNum;
    private String invoiceSeries;
    private String invoiceNumber;
    private Double totalPrice;
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;
    private Double invoiceDebt;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD/MM/yyyy")
    private LocalDate submissionDate;

    private LocalDate deliveryDate;

    @JsonProperty("company")
    private CompanyDto companyDto;

    @JsonProperty("supplier")
    private SupplierDto supplierDto;

}
