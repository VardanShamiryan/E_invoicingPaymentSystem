package com.example.e_invoicingpaymentsystem.controller;

import com.example.e_invoicingpaymentsystem.dto.security.LogInDto;
import com.example.e_invoicingpaymentsystem.dto.security.SignUpDto;
import com.example.e_invoicingpaymentsystem.mapper.CompanyMapper;
import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.model.Role;
import com.example.e_invoicingpaymentsystem.repository.CompanyRepository;
import com.example.e_invoicingpaymentsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          CompanyRepository companyRepository,
                          RoleRepository roleRepository,
                          CompanyMapper companyMapper) {
        this.authenticationManager = authenticationManager;
        this.companyRepository = companyRepository;
        this.roleRepository = roleRepository;
        this.companyMapper = companyMapper;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LogInDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getTin(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Company signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        // add check for tin exists in a DB
        if (companyRepository.existsByTin(signUpDto.getTin())) {
            return new ResponseEntity<>(
                    "Company with this tin is already exists!", HttpStatus.BAD_REQUEST);
        }

        // create company object
        Company company = companyMapper.toCompanyFromSignUpDto(signUpDto);

        // role table-ic vercnum enq mi hat role, u company-in talis enq 1t role-@
        // singleton-@ nshanakuma, vor company-n chi kara urish role unena
        Role roles = roleRepository.findByRole("ROLE_USER");
        company.setRoles(Collections.singleton(roles));

        companyRepository.save(company);

        return new ResponseEntity<>("Company registered successfully", HttpStatus.OK);
    }
}