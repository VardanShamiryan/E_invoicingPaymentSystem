package com.example.e_invoicingpaymentsystem.mapper;

import com.example.e_invoicingpaymentsystem.dto.SupplierDto;
import com.example.e_invoicingpaymentsystem.model.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {
    public SupplierDto toSupplierDto(Supplier supplier){
        SupplierDto supplierDto=new SupplierDto();
        supplierDto.setSupplierName(supplier.getSupplierName());
        supplierDto.setSuppAccountNumber(supplier.getSuppAccountNumber());
        supplierDto.setSupplierTin(supplier.getSupplierTin());
        return  supplierDto;
    }

    public Supplier toSupplier(SupplierDto supplierDto){
        Supplier supplier=new Supplier();
        supplier.setSupplierName(supplierDto.getSupplierName());
        supplier.setSuppAccountNumber(supplierDto.getSuppAccountNumber());
        supplier.setSupplierTin(supplierDto.getSupplierTin());
        return supplier;
    }
}
