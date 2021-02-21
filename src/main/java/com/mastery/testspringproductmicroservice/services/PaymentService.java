package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.response.MakePaymentResponseDto;
import com.mastery.testspringproductmicroservice.entities.Invoice;
import com.mastery.testspringproductmicroservice.entities.Payment;
import com.mastery.testspringproductmicroservice.repositories.InvoiceRepository;
import com.mastery.testspringproductmicroservice.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor  // dependency injection via constructor is achieved by AllArgsConstructor Lombok annotation.
@Service
public class PaymentService {

    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    public ResponseEntity<MakePaymentResponseDto> makePayment(int invoiceId) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        // payment is acceptable if and only if there is a invoice with the provided invoiceId in the database
        if (optionalInvoice.isPresent()){
            try {
                Payment payment = new Payment();
                payment.setInvoice(optionalInvoice.get());
                payment.setTime(LocalDateTime.now());
                payment.setAmount(optionalInvoice.get().getAmount());
                paymentRepository.save(payment);

                MakePaymentResponseDto success = new MakePaymentResponseDto(payment,"SUCCESS");
                return ResponseEntity.ok().body(success);

            } catch (RuntimeException e){
                MakePaymentResponseDto failed = new MakePaymentResponseDto(new Payment(),"FAILED");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failed);
            }
        }
        MakePaymentResponseDto failed= new MakePaymentResponseDto(new Payment(),"FAILED");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(failed);
    }

    public ResponseEntity<Payment> findPaymentDetailsByPaymentId(int paymentId){
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()){
            return ResponseEntity.ok().body(optionalPayment.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
