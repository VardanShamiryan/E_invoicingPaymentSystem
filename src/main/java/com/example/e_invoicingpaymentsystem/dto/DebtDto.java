package com.example.e_invoicingpaymentsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DebtDto {
    @JsonProperty("supplier")
    private SupplierDto supplierDto;
    private Double totalDebt;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("Supplier Tin", supplierDto.getSupplierTin())
                .append("Supplier name", supplierDto.getSupplierName())
                .append("total_debt", totalDebt)
                .toString();
    }
}
