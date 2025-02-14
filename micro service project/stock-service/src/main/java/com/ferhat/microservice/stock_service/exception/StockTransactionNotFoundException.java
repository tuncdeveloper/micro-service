package com.ferhat.microservice.stock_service.exception;

import java.util.UUID;

public class StockTransactionNotFoundException extends RuntimeException{
    public StockTransactionNotFoundException(UUID id){
        super("StockTransaction not found with "+id);
    }
}
