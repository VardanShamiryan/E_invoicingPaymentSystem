package com.example.e_invoicingpaymentsystem.repository;

import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.model.Debt;
import com.example.e_invoicingpaymentsystem.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {

    Debt findDebtByCompanyAndSupplier(Company company, Supplier supplier);

    boolean existsDebtByCompanyAndSupplier(Company company, Supplier supplier);
}
