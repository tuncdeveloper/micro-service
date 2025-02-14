package com.ferhat.microservice.order_item_service.repository;

import com.ferhat.microservice.order_item_service.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    List<OrderItem> findByQuantity(Integer quantity);
}
