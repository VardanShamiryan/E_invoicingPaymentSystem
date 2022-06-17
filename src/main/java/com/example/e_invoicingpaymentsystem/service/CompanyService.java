package com.example.e_invoicingpaymentsystem.service;

import com.example.e_invoicingpaymentsystem.dto.SignUpDto;
import com.example.e_invoicingpaymentsystem.mapper.CompanyMapper;
import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.repository.CompanyRepository;
import com.example.e_invoicingpaymentsystem.util.JwtTokenUtil;
import com.example.e_invoicingpaymentsystem.validator.CompanyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private CompanyMapper companyMapper;
    private CompanyValidator companyValidator;
    private JwtTokenUtil jwtTokenUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CompanyService(CompanyRepository companyRepository,
                          CompanyMapper companyMapper,
                          CompanyValidator companyValidator,
                          JwtTokenUtil jwtTokenUtil,
                          BCryptPasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companyValidator = companyValidator;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> createCompany(SignUpDto signUpDto) {
        if (companyValidator.isValidCompany(signUpDto)) {
            Company company = companyMapper.toCompany(signUpDto);

            if (companyRepository.existsByTin(company.getTin())) {
                return new ResponseEntity<>("Company with this TIN already exists!", HttpStatus.BAD_REQUEST);
            }
            if (companyRepository.existsCompanyByCompanyName(company.getCompanyName())) {
                return new ResponseEntity<>("Company with this name already exists!", HttpStatus.BAD_REQUEST);
            }
            if (companyRepository.existsCompanyByCompAccountNumber(company.getCompAccountNumber())) {
                return new ResponseEntity<>("Company with this account number already exists!",
                        HttpStatus.BAD_REQUEST);
            }

            companyRepository.save(company);
            return new ResponseEntity<>("Company signed-up successfully!.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company is not valid!", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> deleteCompany(String tin) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(tin)) {
            Long companyId = companyRepository.findByTin(tin).getId();
            companyRepository.deleteById(companyId);
            return new ResponseEntity<>("Company is deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("You can delete only your company!", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> updateCompany(SignUpDto signUpDto, String tin) {
        if (companyValidator.isValidCompanyDto(signUpDto)) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            if (!currentPrincipalName.equals(tin)){
                return new ResponseEntity<>("You can update only your company!", HttpStatus.BAD_REQUEST);
            }
            if (companyRepository.existsCompanyByCompanyName(signUpDto.getCompanyName())) {
                return new ResponseEntity<>("Company with this name already exists!", HttpStatus.BAD_REQUEST);
            }
            if (companyRepository.existsCompanyByCompAccountNumber(signUpDto.getCompAccountNumber())) {
                return new ResponseEntity<>("Company with this account number already exists!", HttpStatus.BAD_REQUEST);
            }

            Company company = companyRepository.findByTin(tin);
            company.setCompanyName(signUpDto.getCompanyName());
            company.setEmail(signUpDto.getEmail());
            company.setPassword(signUpDto.getPassword());
            company.setCompAccountNumber(signUpDto.getCompAccountNumber());
            company.setPhoneNumber(signUpDto.getPhoneNumber());
            companyRepository.save(company);
            return new ResponseEntity<>("Company is updated!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company is not valid!", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> findYourCompany() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Company company = companyRepository.findByTin(currentPrincipalName);
        SignUpDto signUpDto = companyMapper.toCompanyDto(company);
        return new ResponseEntity<>(signUpDto.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> signIn(String tin, String password) throws UserPrincipalNotFoundException {
        Company company = companyRepository.findByTin(tin);

        if (!passwordEncoder.matches(password, company.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Wrong password or username");
        }
        return new ResponseEntity<>(jwtTokenUtil.generateToken(company), HttpStatus.OK);
    }
}
