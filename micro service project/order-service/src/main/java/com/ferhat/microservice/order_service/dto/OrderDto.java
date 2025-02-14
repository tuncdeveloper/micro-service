package com.ferhat.microservice.order_service.dto;


import com.ferhat.microservice.order_service.model.OrderStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderDto {

    private UUID id;
    private UUID customerId;
    private OrderStatusEnum status;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
