package com.example.e_invoicingpaymentsystem.mapper;

import com.example.e_invoicingpaymentsystem.dto.DebtDto;
import com.example.e_invoicingpaymentsystem.model.Debt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DebtMapper {
    SupplierMapper supplierMapper;

    public DebtMapper(SupplierMapper supplierMapper) {
        this.supplierMapper = supplierMapper;
    }

    public DebtDto toDebtDto(Debt debt) {
        DebtDto debtDto = new DebtDto();
        debtDto.setSupplierDto(supplierMapper.toSupplierDto(debt.getSupplier()));
        debtDto.setTotalDebt(debt.getTotalDebt());
        return debtDto;
    }
    public List<DebtDto> toDebtDtoList(List<Debt> debtList){

        return debtList.stream().map(this::toDebtDto).collect(Collectors.toList());

    }
}
