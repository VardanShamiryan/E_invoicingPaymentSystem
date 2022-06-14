package com.example.e_invoicingpaymentsystem.repository;

import com.example.e_invoicingpaymentsystem.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsSupplierBySupplierTin(String supplierTin);

    Supplier getSupplierBySupplierTin(String supplierTin);
}
