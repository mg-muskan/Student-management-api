package com.starter.springboot.payment;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
// If this property exists then create this class bean means by conditional
@ConditionalOnProperty(name = "payment.provider", havingValue = "razorpay")
public class RazorPayService implements PaymentService {

    @Override
    public String pay() {
        String payment = "RazorPay";
        System.out.println("Payment from: " + payment);
        return payment;
    }
}
