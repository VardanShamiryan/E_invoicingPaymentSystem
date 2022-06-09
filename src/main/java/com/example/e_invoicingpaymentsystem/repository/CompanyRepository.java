package com.example.e_invoicingpaymentsystem.repository;

import com.example.e_invoicingpaymentsystem.model.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.tomcat.jni.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {


    boolean existsByTinAndCompanyNameAndCompAccountNumberAndEmailAndPhoneNumberAndPassword(
            String tin,
            String companyName,
            String compAccountNumber,
            String email,
            String phoneNumber,
            String password
    );

    default boolean companyExists(Company company) {
        return existsByTinAndCompanyNameAndCompAccountNumberAndEmailAndPhoneNumberAndPassword(
                company.getTin(),
                company.getCompanyName(),
                company.getCompAccountNumber(),
                company.getEmail(),
                company.getPhoneNumber(),
                company.getPassword()
        );
    }


    boolean existsCompanyByCompanyName(String companyName);
    boolean existsByCompAccountNumber(String companyAccountNumber);
    boolean existsByEmail(String email);
    boolean deleteCompanyByTin(String tin);
    Company findByTin(String tin);

    boolean existsByTin(String tin);

}
