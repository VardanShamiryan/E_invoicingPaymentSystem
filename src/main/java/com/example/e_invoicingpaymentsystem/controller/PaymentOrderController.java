package com.example.e_invoicingpaymentsystem.controller;

import com.example.e_invoicingpaymentsystem.service.PaymentOrderService;
import org.apache.http.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
@RestController
@RequestMapping("/api/payment")

public class PaymentOrderController {

    PaymentOrderService paymentOrderService;

    public PaymentOrderController(PaymentOrderService paymentOrderService) {
        this.paymentOrderService = paymentOrderService;
    }

    @PutMapping("/transfer")
    public ResponseEntity<?> transferFromAccountToAccount(@RequestParam Double amount,
                                                       @RequestParam String fromAccountNumber,
                                                       @RequestParam String toAccountNumber) {
        return paymentOrderService.transferFromAccountToAccount(amount,
                fromAccountNumber,
                toAccountNumber);
    }

}