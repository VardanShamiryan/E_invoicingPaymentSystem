package com.example.e_invoicingpaymentsystem.repository;

import com.example.e_invoicingpaymentsystem.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {

}
