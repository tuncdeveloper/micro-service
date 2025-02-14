package com.ferhat.microservice.order_item_service.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderItemDto {

    private UUID id;
    private UUID orderId;
    private UUID productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
