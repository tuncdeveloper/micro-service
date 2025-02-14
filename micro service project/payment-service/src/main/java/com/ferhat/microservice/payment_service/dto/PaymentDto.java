package com.ferhat.microservice.payment_service.dto;


import com.ferhat.microservice.payment_service.model.PaymentMethodEnum;
import com.ferhat.microservice.payment_service.model.PaymentStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentDto {
    private UUID id;
    private UUID orderId;  // sadece orderId kullanıyoruz
    private PaymentStatusEnum paymentStatusEnum;
    private PaymentMethodEnum paymentMethodEnum;
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
