package com.ferhat.microservice.stock_service.dto;

import com.ferhat.microservice.stock_service.model.StockTransactionEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StockTransactionDto {

    private UUID stockTransactionId;
    private UUID productId;
    private Integer quantityChanged;
    private StockTransactionEnum transactionType;
    private LocalDateTime createdAt;

}
