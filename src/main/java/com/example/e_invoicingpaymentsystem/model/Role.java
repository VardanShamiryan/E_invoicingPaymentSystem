package com.example.e_invoicingpaymentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Column(name = "role", length = 60)
    private String role="ROLE_USER";
}
