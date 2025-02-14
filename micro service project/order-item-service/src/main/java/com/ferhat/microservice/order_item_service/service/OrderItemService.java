package com.ferhat.microservice.order_item_service.service;

import com.ferhat.microservice.order_item_service.dto.OrderItemDto;
import com.ferhat.microservice.order_item_service.exception.OrderItemNotFoundException;
import com.ferhat.microservice.order_item_service.mapper.OrderItemMapper;
import com.ferhat.microservice.order_item_service.model.OrderItem;
import com.ferhat.microservice.order_item_service.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    // CREATE: Yeni sipariş kalemi ekleme
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemMapper.mapToEntity(orderItemDto);
        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.mapToDto(orderItem);
    }

    // READ: Belirli bir OrderItem getir
    public OrderItemDto getOrderItemById(UUID id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException(id));
        return orderItemMapper.mapToDto(orderItem);
    }

    // READ: Tüm sipariş kalemlerini getir
    public List<OrderItemDto> getAllOrderItems( ) {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItemMapper.mapToDtoList(orderItems);
    }

    // UPDATE: Sipariş kalemini güncelle
    @Transactional
    public OrderItemDto updateOrderItem(UUID id, OrderItemDto orderItemDto) {
        Optional<OrderItem> existingOrderItemOpt = orderItemRepository.findById(id);
        if (existingOrderItemOpt.isPresent()) {
            OrderItem existingOrderItem = existingOrderItemOpt.get();
            existingOrderItem.setOrderId(orderItemDto.getOrderId());
            existingOrderItem.setProductId(orderItemDto.getProductId());
            existingOrderItem.setQuantity(orderItemDto.getQuantity());
            existingOrderItem.setUnitPrice(orderItemDto.getUnitPrice());
            existingOrderItem.setTotalPrice(orderItemDto.getTotalPrice());
            existingOrderItem = orderItemRepository.save(existingOrderItem);
            return orderItemMapper.mapToDto(existingOrderItem);
        }
        return null;
    }

    public List<OrderItemDto> getAllOrderItemByQuantity(Integer quantity){
        List<OrderItem> orderItems = orderItemRepository.findByQuantity(quantity);
        return orderItemMapper.mapToDtoList(orderItems);
    }


    // DELETE: Sipariş kalemini sil
    public void deleteOrderItem(UUID id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                        .orElseThrow(()->new OrderItemNotFoundException(id));
        orderItemRepository.delete(orderItem);
    }
}
