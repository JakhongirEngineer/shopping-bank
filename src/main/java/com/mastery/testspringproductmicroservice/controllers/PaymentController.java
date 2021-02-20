package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.dtos.response.MakePaymentResponseDto;
import com.mastery.testspringproductmicroservice.entities.Payment;
import com.mastery.testspringproductmicroservice.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
public class PaymentController {
    // services are injected here
    private final PaymentService paymentService;
    @PostMapping("/payment")
    public ResponseEntity<MakePaymentResponseDto> makePayment(@RequestParam("invoice_id") int invoiceId){ // DTO must be created;
        return paymentService.makePayment(invoiceId);
    }

    @GetMapping("/payment/details")
    public ResponseEntity<Payment> getPaymentDetails(@RequestParam(name = "id") int paymentId){
        return paymentService.findPaymentDetailsByPaymentId(paymentId);
    }
}
