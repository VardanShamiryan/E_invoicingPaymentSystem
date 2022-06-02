package com.example.e_invoicingpaymentsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DebtDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
    private Double total_debt;

    @JsonProperty("company")
    private CompanyDto companyDto;

    @JsonProperty("supplier")
    private SupplierDto supplierDto;

}
