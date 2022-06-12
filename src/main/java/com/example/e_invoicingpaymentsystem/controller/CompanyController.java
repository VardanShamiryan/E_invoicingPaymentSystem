package com.example.e_invoicingpaymentsystem.controller;

import com.example.e_invoicingpaymentsystem.dto.CompanyDto;
import com.example.e_invoicingpaymentsystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/company")
public class CompanyController {

    CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> createCompany(@RequestBody CompanyDto companyDto) {

        if (companyDto == null) {
            return new ResponseEntity<>(
                    "Please enter all fields", HttpStatus.BAD_REQUEST);
        }
        return companyService.createCompany(companyDto);
    }

    @DeleteMapping("/{tin}")
    public ResponseEntity<?> deleteCompany(@PathVariable("tin") String tin) {
        return companyService.deleteCompany(tin);
    }

    @PutMapping("/{tin}")
    public ResponseEntity<?> updateCompany(@RequestBody CompanyDto companyDto,
                                           @PathVariable("tin") String tin) {
        return companyService.updateCompany(companyDto, tin);
    }
}
