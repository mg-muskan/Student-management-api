package com.starter.springboot;

import com.starter.springboot.payment.PaymentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarterSpringBootApplication implements CommandLineRunner {

    private final PaymentService paymentService;

    public StarterSpringBootApplication(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public static void main(String[] args) {
		SpringApplication.run(StarterSpringBootApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        String payment = paymentService.pay();
        System.out.println("Payment Done: " + payment);
    }
}
