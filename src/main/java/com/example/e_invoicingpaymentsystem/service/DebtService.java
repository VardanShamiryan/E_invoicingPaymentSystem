package com.example.e_invoicingpaymentsystem.service;

import com.example.e_invoicingpaymentsystem.mapper.DebtMapper;
import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.model.Debt;
import com.example.e_invoicingpaymentsystem.model.Invoice;
import com.example.e_invoicingpaymentsystem.repository.CompanyRepository;
import com.example.e_invoicingpaymentsystem.repository.DebtRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtService {

    private DebtRepository debtRepository;
    private CompanyRepository companyRepository;
    private DebtMapper debtMapper;

    public DebtService(DebtRepository debtRepository, CompanyRepository companyRepository, DebtMapper debtMapper) {
        this.debtRepository = debtRepository;
        this.companyRepository = companyRepository;
        this.debtMapper = debtMapper;
    }

    public ResponseEntity<?> findAllDebts() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Company company = companyRepository.findByTin(currentPrincipalName);
        List<Debt> debtList = debtRepository.findDebtByCompany(company);
        return new ResponseEntity<>(debtMapper.toDebtDtoList(debtList), HttpStatus.OK);
    }
}
