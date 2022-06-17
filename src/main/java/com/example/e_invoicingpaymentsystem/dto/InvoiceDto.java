package com.example.e_invoicingpaymentsystem.dto;

import com.example.e_invoicingpaymentsystem.model.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private String invoiceNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate deliveryDate;
    private String suppAccountNumber;
    private Double totalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate submissionDate;
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;
    private Double invoiceDebt;
    @JsonProperty("company")
    private SignUpDto signUpDto;
    @JsonProperty("supplier")
    private SupplierDto supplierDto;

    private String adjustmentInvoiceNumber;
    @Override
    public String toString() {
        return "InvoiceDto{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", suppAccountNumber='" + suppAccountNumber + '\'' +
                ", totalPrice=" + totalPrice +
                ", submissionDate=" + submissionDate +
                ", paymentStatus=" + paymentStatus +
                ", invoiceDebt=" + invoiceDebt +
                "}\n";
    }
}
