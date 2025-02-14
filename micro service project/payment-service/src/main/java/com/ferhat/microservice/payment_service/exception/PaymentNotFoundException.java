package com.ferhat.microservice.payment_service.exception;

import org.springframework.data.domain.Page;

import java.util.UUID;

public class PaymentNotFoundException extends RuntimeException{

    public PaymentNotFoundException(UUID id){
        super("Payment not found with "+id);
    }

}
