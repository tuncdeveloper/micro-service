package com.ferhat.microservice.stock_service.service;

import com.ferhat.microservice.stock_service.dto.StockTransactionDto;
import com.ferhat.microservice.stock_service.exception.StockTransactionNotFoundException;
import com.ferhat.microservice.stock_service.mapper.StockTransactionMapper;
import com.ferhat.microservice.stock_service.model.StockTransaction;
import com.ferhat.microservice.stock_service.repository.StockTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StockTransactionService {
    private final StockTransactionRepository stockTransactionRepository;
    private final StockTransactionMapper stockTransactionMapper;

    public StockTransactionService(StockTransactionRepository stockTransactionRepository
            , StockTransactionMapper stockTransactionMapper) {
        this.stockTransactionRepository = stockTransactionRepository;
        this.stockTransactionMapper = stockTransactionMapper;
    }


    public List<StockTransactionDto> getAllStockTransactions() {
        List<StockTransaction> stockTransactions = stockTransactionRepository.findAll();
        return stockTransactionMapper.mapToDtoList(stockTransactions);
    }

    public StockTransactionDto getStockTransactionById(UUID id) {
        StockTransaction stockTransaction = stockTransactionRepository.findById(id)
                .orElseThrow(() -> new StockTransactionNotFoundException(id));
        return stockTransactionMapper.mapToDto(stockTransaction);
    }


    public StockTransactionDto createStockTransaction(StockTransactionDto stockTransactionDto) {
        StockTransaction stockTransaction = stockTransactionMapper.mapToEntity(stockTransactionDto);
        StockTransaction saveStockTransaction = stockTransactionRepository.save(stockTransaction);
        return stockTransactionMapper.mapToDto(saveStockTransaction);
    }

    public StockTransactionDto updateStockTransaction(UUID id, StockTransactionDto stockTransactionDto) {

        StockTransaction existingStockTransaction = stockTransactionRepository.findById(id)
                .orElseThrow(() -> new StockTransactionNotFoundException(id));

        existingStockTransaction.setStockTransactionId(stockTransactionDto.getStockTransactionId());
        existingStockTransaction.setProductId(stockTransactionDto.getProductId());
        existingStockTransaction.setTransactionType(stockTransactionDto.getTransactionType());
        existingStockTransaction.setCreatedAt(stockTransactionDto.getCreatedAt());
        existingStockTransaction.setQuantityChanged(stockTransactionDto.getQuantityChanged());

        StockTransaction updatedStockTransaction = stockTransactionRepository.save(existingStockTransaction);
        return stockTransactionMapper.mapToDto(updatedStockTransaction);
    }

    
    public void deleteStockTransaction(UUID id) {
        StockTransaction stockTransaction = stockTransactionRepository.findById(id)
                .orElseThrow(() -> new StockTransactionNotFoundException(id));
        stockTransactionRepository.delete(stockTransaction);
    }
}

