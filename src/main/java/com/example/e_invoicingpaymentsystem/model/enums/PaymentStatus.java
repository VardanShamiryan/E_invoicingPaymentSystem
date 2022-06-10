package com.example.e_invoicingpaymentsystem.model.enums;

public enum PaymentStatus {

    UNPAID (0, "UNPAID"),
    SENT(1, "SENT"),
    PARTIALLY_PAID(2,"PARTIALLY_PAID"),
    PAID(3, "PAID");


    int id;
    String type;

    PaymentStatus(int id, String type) {
        this.id = id;
        this.type = type;
    }
}
