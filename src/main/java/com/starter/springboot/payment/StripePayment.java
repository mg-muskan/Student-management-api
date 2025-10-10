package com.starter.springboot.payment;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.provider", havingValue = "stripe")
public class StripePayment implements PaymentService {

    @Override
    public String pay() {
        String payment = "Stripe";
        System.out.println("Payment from: " + payment);
        return payment;
    }
}
