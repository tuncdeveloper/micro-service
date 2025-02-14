package com.ferhat.microservice.order_item_service.mapper;

import com.ferhat.microservice.order_item_service.dto.OrderItemDto;
import com.ferhat.microservice.order_item_service.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemDto>{

    @Override
    OrderItem mapToEntity(OrderItemDto Dto);
    @Override
    OrderItemDto mapToDto(OrderItem Entity);

    @Override
    List<OrderItem> mapToEntityList(List<OrderItemDto> orderItemDtoList);

    @Override
    List<OrderItemDto> mapToDtoList(List<OrderItem> orderItemList);

}
