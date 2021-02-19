package com.mastery.testspringproductmicroservice.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @NotNull(message = "time in Payment table cannot be null")
    private LocalDateTime time;

    @NotNull(message = "amount in Payment table cannot be null")
    @Column(precision = 8, scale = 2)
    private BigDecimal amount;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "invoice_id")
   private Invoice invoice;

    // many-to-one relationship with Invoice

}
