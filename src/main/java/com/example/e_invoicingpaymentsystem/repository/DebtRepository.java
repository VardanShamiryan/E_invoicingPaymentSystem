package com.example.e_invoicingpaymentsystem.repository;

import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.model.Debt;
import com.example.e_invoicingpaymentsystem.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {

    Debt findDebtByCompanyAndSupplier(Company company, Supplier supplier);

    List<Debt> findDebtByCompany(Company company);

    boolean existsDebtByCompanyAndSupplier(Company company, Supplier supplier);
}
