package com.ferhat.microservice.order_item_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;
@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue()
    @Column(name = "order_items_id", nullable = false)
    private UUID id;

    @Column(name = "order_id_fk", nullable = false)
    private UUID orderId;

    @Column(name = "product_id_fk", nullable = false)
    private UUID productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
}

