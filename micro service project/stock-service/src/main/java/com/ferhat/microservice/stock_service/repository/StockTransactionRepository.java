package com.ferhat.microservice.stock_service.repository;

import com.ferhat.microservice.stock_service.model.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, UUID> {
}
