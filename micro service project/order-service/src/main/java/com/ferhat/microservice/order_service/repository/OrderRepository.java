package com.ferhat.microservice.order_service.repository;

import com.ferhat.microservice.order_service.model.Order;
import com.ferhat.microservice.order_service.model.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByStatus(OrderStatusEnum status);

    List<Order> findByTotalPriceBetween(Double min, Double max);
}
