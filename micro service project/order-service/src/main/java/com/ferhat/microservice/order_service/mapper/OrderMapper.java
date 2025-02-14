package com.ferhat.microservice.order_service.mapper;

import com.ferhat.microservice.order_service.dto.OrderDto;
import com.ferhat.microservice.order_service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface OrderMapper extends BaseMapper<Order, OrderDto>{

    @Override
    Order mapToEntity(OrderDto Dto);
    @Override
    OrderDto mapToDto(Order Entity);

    @Override
    List<Order> mapToEntityList(List<OrderDto> dtoList);
    @Override
    List<OrderDto> mapToDtoList(List<Order> list);

}
