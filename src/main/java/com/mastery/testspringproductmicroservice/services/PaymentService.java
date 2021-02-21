package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.models.dtos.response.MakePaymentResponseDto;
import com.mastery.testspringproductmicroservice.models.entities.Invoice;
import com.mastery.testspringproductmicroservice.models.entities.Payment;
import com.mastery.testspringproductmicroservice.exceptions.ResourceNotFoundException;
import com.mastery.testspringproductmicroservice.repositories.InvoiceRepository;
import com.mastery.testspringproductmicroservice.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor  // dependency injection via constructor is achieved by AllArgsConstructor Lombok annotation.
@Service
public class PaymentService {

    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    public MakePaymentResponseDto makePayment(int invoiceId) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        // payment is acceptable if and only if there is a invoice with the provided invoiceId in the database
        if (optionalInvoice.isPresent()){
            try {
                Payment payment = new Payment();
                payment.setInvoice(optionalInvoice.get());
                payment.setTime(LocalDateTime.now());
                payment.setAmount(optionalInvoice.get().getAmount());
                paymentRepository.save(payment);

                return new MakePaymentResponseDto(payment,"SUCCESS");

            } catch (RuntimeException e){
                return new MakePaymentResponseDto(new Payment(),"FAILED");
            }
        }
        return new MakePaymentResponseDto(new Payment(),"FAILED");
    }

    public Payment findPaymentDetailsByPaymentId(int paymentId){
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()){
            return optionalPayment.get();
        }
        throw new ResourceNotFoundException("payment with the paymentId: "+ paymentId +" is not found");
    }
}
