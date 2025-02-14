package com.ferhat.microservice.order_item_service.exception;

import java.util.UUID;

public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException(UUID orderId){
        super("OrderItem not found with "+ orderId);
    }
}
