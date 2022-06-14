package com.example.e_invoicingpaymentsystem.mapper;

import com.example.e_invoicingpaymentsystem.dto.ImportedXmlDto;
import com.example.e_invoicingpaymentsystem.model.Supplier;
import com.example.e_invoicingpaymentsystem.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FromImportedXmlDtoToSupplier {
    SupplierRepository supplierRepository;

    @Autowired
    public FromImportedXmlDtoToSupplier(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier fromImportedXmlDtoToSupplier(ImportedXmlDto importedXmlDto) {
        if (!supplierRepository.existsSupplierBySupplierTin(importedXmlDto.getSupplierTin())) {
            Supplier supplier = new Supplier();
            supplier.setSupplierTin(importedXmlDto.getSupplierTin());
            supplier.setSupplierName(importedXmlDto.getSupplierName());
            supplier.setSuppAccountNumber(importedXmlDto.getSuppAccountNumber());
            supplierRepository.save(supplier);
            return supplier;
        }
        return supplierRepository.getSupplierBySupplierTin(importedXmlDto.getSupplierTin());
    }
}
