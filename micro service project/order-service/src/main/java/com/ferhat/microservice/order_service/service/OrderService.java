package com.ferhat.microservice.order_service.service;



import com.ferhat.microservice.order_service.dto.OrderDto;
import com.ferhat.microservice.order_service.exception.OrderNotFoundException;
import com.ferhat.microservice.order_service.mapper.OrderMapper;
import com.ferhat.microservice.order_service.model.Order;
import com.ferhat.microservice.order_service.model.OrderStatusEnum;
import com.ferhat.microservice.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository,OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    // CREATE: Yeni sipariş oluşturma
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.mapToEntity(orderDto);
        order = orderRepository.save(order);
        return orderMapper.mapToDto(order);
    }

    // READ: Siparişi ID'ye göre getirme
    public OrderDto getOrderById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.mapToDto(order);
    }

    // READ: Tüm siparişleri listeleme
    public List<OrderDto> getAllOrders( ) {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.mapToDtoList(orders);
    }

    // UPDATE: Siparişi güncelleme
    public OrderDto updateOrder(UUID id, OrderDto orderDto) {
        Optional<Order> existingOrderOpt = orderRepository.findById(id);
        if (existingOrderOpt.isPresent()) {
            Order existingOrder = existingOrderOpt.get();
            existingOrder.setStatus(orderDto.getStatus());
            existingOrder.setTotalPrice(orderDto.getTotalPrice());
            existingOrder.setUpdatedAt(orderDto.getUpdatedAt());
            existingOrder = orderRepository.save(existingOrder);
            return orderMapper.mapToDto(existingOrder);
        }
        return null;
    }

    // DELETE: Siparişi silme
    public void deleteOrder(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));  // Sipariş bulunamazsa exception fırlatılır
        orderRepository.delete(order);
    }


    //Statusa göre orderleri getirme
    public List<OrderDto> getOrderByStatus(OrderStatusEnum orderStatusEnum){
        List<Order> orders = orderRepository.findByStatus(orderStatusEnum);  // Status'a göre siparişleri al
        List<OrderDto> orderDtos = orderMapper.mapToDtoList(orders);
        return orderDtos;
    }

    //price göre orderleri getirme
    public List<OrderDto> getOrderByTotalPrice(Double min , Double max){
        List<Order> orders = orderRepository.findByTotalPriceBetween(min,max);
        List<OrderDto> orderDtos = orderMapper.mapToDtoList(orders);
        return orderDtos;
    }


}

