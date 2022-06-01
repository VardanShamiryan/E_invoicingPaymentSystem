package com.example.e_invoicingpaymentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {

    private String supplierName;
    private String suppAccountNumber;
    private String supplierTin;

}
