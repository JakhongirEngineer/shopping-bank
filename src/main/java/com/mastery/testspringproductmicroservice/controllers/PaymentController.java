package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.models.dtos.response.MakePaymentResponseDto;
import com.mastery.testspringproductmicroservice.models.entities.Payment;
import com.mastery.testspringproductmicroservice.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor // dependencies are automatically injected because of Lombok's All arguments constructor
@RestController
public class PaymentController {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final PaymentService paymentService;
    @PostMapping("/payment")
    public ResponseEntity<MakePaymentResponseDto> makePayment(@RequestParam("invoice_id") int invoiceId){ // DTO must be created;
        MakePaymentResponseDto makePaymentResponseDto = paymentService.makePayment(invoiceId);
        if (makePaymentResponseDto.getPaymentStatus().equals("FAILED")){
            return new ResponseEntity<>(makePaymentResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(makePaymentResponseDto, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/payment/details")
    public ResponseEntity<Payment> getPaymentDetails(@RequestParam(name = "id") int paymentId){
        Payment payment = paymentService.findPaymentDetailsByPaymentId(paymentId);
        return new ResponseEntity<>(payment,HttpStatus.ACCEPTED);
    }
}
