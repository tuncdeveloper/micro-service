package com.ferhat.microservice.stock_service.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "stock_transactions")
public class StockTransaction {

    @Id
    @GeneratedValue
    private UUID stockTransactionId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "quantity_changed", nullable = false)
    private Integer quantityChanged;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private StockTransactionEnum transactionType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;



}
