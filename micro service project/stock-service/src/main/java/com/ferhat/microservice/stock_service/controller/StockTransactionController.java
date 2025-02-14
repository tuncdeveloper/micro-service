package com.ferhat.microservice.stock_service.controller;

import com.ferhat.microservice.stock_service.dto.StockTransactionDto;
import com.ferhat.microservice.stock_service.service.StockTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stockTransactions")
public class StockTransactionController {

    private final StockTransactionService stockTransactionService;

    public StockTransactionController(StockTransactionService stockTransactionService) {
        this.stockTransactionService = stockTransactionService;
    }


    // Tüm stok işlemlerini getir
    @GetMapping("/getAll")
    public ResponseEntity<List<StockTransactionDto>> getAllStockTransactions() {
        List<StockTransactionDto> stockTransactions = stockTransactionService.getAllStockTransactions();
        return ResponseEntity.ok(stockTransactions);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<StockTransactionDto> getStockTransactionById(@PathVariable UUID id) {
        StockTransactionDto stockTransactionDto = stockTransactionService.getStockTransactionById(id);
        return ResponseEntity.ok(stockTransactionDto);
    }

    // Yeni stok işlemi ekle
    @PostMapping("/create")
    public ResponseEntity<StockTransactionDto> createStockTransaction(@RequestBody StockTransactionDto stockTransactionDto) {
        StockTransactionDto createdStockTransaction = stockTransactionService.createStockTransaction(stockTransactionDto);
        return ResponseEntity.ok(createdStockTransaction);
    }

    // Stok işlemi güncelle
    @PutMapping("/update/{id}")
    public ResponseEntity<StockTransactionDto> updateStockTransaction(@PathVariable UUID id,
                                                                      @RequestBody StockTransactionDto stockTransactionDto) {
        StockTransactionDto updatedStockTransaction = stockTransactionService.updateStockTransaction(id, stockTransactionDto);
        return ResponseEntity.ok(updatedStockTransaction);
    }

    // Stok işlemi sil
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStockTransaction(@PathVariable UUID id) {
        stockTransactionService.deleteStockTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
