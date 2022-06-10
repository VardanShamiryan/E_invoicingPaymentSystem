package com.example.e_invoicingpaymentsystem.service;

import com.example.e_invoicingpaymentsystem.dto.CompanyDto;
import com.example.e_invoicingpaymentsystem.mapper.CompanyMapper;
import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.repository.CompanyRepository;
import com.example.e_invoicingpaymentsystem.validator.CompanyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    CompanyRepository companyRepository;
    CompanyMapper companyMapper;
    CompanyValidator companyValidator;
    @Autowired
    public CompanyService(CompanyRepository companyRepository,
                          CompanyMapper companyMapper,
                          CompanyValidator companyValidator) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companyValidator = companyValidator;

    }



    public ResponseEntity<?> createCompany(CompanyDto companyDto) {
        CompanyDto companyDto1;
        if (companyValidator.isValidCompany(companyDto)) {
            Company company = companyMapper.toCompany(companyDto);
            if (companyRepository.existsByTin(company.getTin())) {
                return new ResponseEntity<>("Company with this TIN already exists!", HttpStatus.BAD_REQUEST);
            }

            Company savedCompany = companyRepository.save(company);

            return new ResponseEntity<>("Company signed-up successfully!.", HttpStatus.OK);

        }
        return new ResponseEntity<>("Company is not valid!",HttpStatus.BAD_REQUEST);
    }
}