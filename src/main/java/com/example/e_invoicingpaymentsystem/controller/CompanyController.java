package com.example.e_invoicingpaymentsystem.controller;

import com.example.e_invoicingpaymentsystem.dto.SignUpDto;
import com.example.e_invoicingpaymentsystem.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createCompany(@RequestBody SignUpDto signUpDto) {

        if (signUpDto == null) {
            return new ResponseEntity<>(
                    "Please enter all fields", HttpStatus.BAD_REQUEST);
        }
        return companyService.createCompany(signUpDto);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("bytin/{tin}")
    public ResponseEntity<?> deleteCompany(@PathVariable("tin") String tin) {
        return companyService.deleteCompany(tin);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("updatebytin/{tin}")
    public ResponseEntity<?> updateCompany(@RequestBody SignUpDto signUpDto,
                                           @PathVariable("tin") String tin) {
        return companyService.updateCompany(signUpDto, tin);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/company")
    public ResponseEntity<?> findYourCompany() {
        return companyService.findYourCompany();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String tin, @RequestParam String password) throws Exception {
        return companyService.signIn(tin, password);
    }
}
