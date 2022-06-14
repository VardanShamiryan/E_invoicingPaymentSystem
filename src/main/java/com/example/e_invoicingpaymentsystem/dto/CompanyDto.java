package com.example.e_invoicingpaymentsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.springframework.stereotype.Component;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private String tin;
    private String companyName;
    private String compAccountNumber;
    private String email;
    private String phoneNumber;
    private String token;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

   // @JsonIgnore
    public String getPassword() {
        return password;
    }


}
