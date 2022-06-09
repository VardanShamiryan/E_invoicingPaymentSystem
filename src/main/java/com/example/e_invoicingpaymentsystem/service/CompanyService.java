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
        if (companyValidator.isValidCompany(companyDto)) {
            Company company = companyMapper.toCompany(companyDto);

            if (companyRepository.existsByTin(company.getTin())) {
                return new ResponseEntity<>("Company with this TIN already exists!", HttpStatus.BAD_REQUEST);
            }
            if (companyRepository.existsCompanyByCompanyName(company.getCompanyName())) {
                return new ResponseEntity<>("Company with this name already exists!", HttpStatus.BAD_REQUEST);
            }
            if (companyRepository.existsByCompAccountNumber(company.getCompAccountNumber())) {
                return new ResponseEntity<>("Company with this account number already exists!", HttpStatus.BAD_REQUEST);
            }
            if (companyRepository.existsByEmail(company.getEmail())) {
                return new ResponseEntity<>("Company with this email already exists!", HttpStatus.BAD_REQUEST);
            }
            companyRepository.save(company);
            return new ResponseEntity<>("Company signed-up successfully!.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company is not valid!", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> deleteCompany(String tin) {

        if (!companyRepository.existsByTin(tin)) {
            return new ResponseEntity<>("There is no company with such id!", HttpStatus.BAD_REQUEST);
        }

        Long companyId = companyRepository.findByTin(tin).getId();
        companyRepository.deleteById(companyId);
        return new ResponseEntity<>("Company is deleted.", HttpStatus.OK);

    }

    public ResponseEntity<?> updateCompany(CompanyDto companyDto, String tin) {
        if (companyValidator.isValidCompany(companyDto)) {
            if (!companyRepository.existsByTin(tin)) {
                return new ResponseEntity<>("There is no company with such tin!", HttpStatus.BAD_REQUEST);
            }
            Company company = companyMapper.toCompany(companyDto);
            if (companyRepository.companyExists(company)) {
                companyRepository.deleteCompanyByTin(tin);
            }
           // company.setTin(tin);
            companyRepository.save(company);
            return new ResponseEntity<>("Company is updated!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company is not valid!", HttpStatus.BAD_REQUEST);

    }

}