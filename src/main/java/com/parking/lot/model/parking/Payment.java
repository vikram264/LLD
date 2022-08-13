package com.parking.lot.model.parking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

public class Payment {
    String paymentId;
    String ticketId;
    double amount;

    String paymentInitaitedAt;
    String paymentCompletedAt;

    PaymentStatus paymentStatus;

    public Payment(String paymentId, String ticketId, double amount) {
        this.paymentId = paymentId;
        this.ticketId = ticketId;
        this.amount = amount;
    }

    //make payment
}
