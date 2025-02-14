package com.ferhat.microservice.stock_service.mapper;

import com.ferhat.microservice.stock_service.dto.StockTransactionDto;
import com.ferhat.microservice.stock_service.model.StockTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface StockTransactionMapper extends BaseMapper<StockTransaction, StockTransactionDto>{

    StockTransaction mapToEntity(StockTransactionDto productDto);
    StockTransactionDto mapToDto(StockTransaction product);

    List<StockTransaction> mapToEntity(List<StockTransactionDto> stockTransactionDtos);
    List<StockTransactionDto> mapToDto(List<StockTransaction> stockTransactions);



}