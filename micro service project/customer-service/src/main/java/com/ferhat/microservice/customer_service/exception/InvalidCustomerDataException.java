package com.ferhat.microservice.customer_service.exception;

public class InvalidCustomerDataException extends RuntimeException{
    public InvalidCustomerDataException(String message) {
        super(message);
    }
}
