package com.mastery.testspringproductmicroservice.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Payment {
    // services are injected here
    @PostMapping("/payment")
    public String makePayment(@RequestBody String s){ // DTO must be created;
        return "Payment";
    }

    @GetMapping("/payment/details")
    public List<Payment> getPaymentDetails(@RequestParam(name = "id") int paymentDetailsId){
        return new ArrayList<>();
    }
}
