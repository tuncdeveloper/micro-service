package com.ferhat.microservice.order_service.controller;


import com.ferhat.microservice.order_service.dto.OrderDto;
import com.ferhat.microservice.order_service.exception.OrderNotFoundException;
import com.ferhat.microservice.order_service.model.OrderStatusEnum;
import com.ferhat.microservice.order_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // CREATE: Yeni sipariş oluşturma
    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    // READ: Siparişi ID'ye göre getirme
    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable UUID id) {
        try {
            OrderDto order = orderService.getOrderById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // READ: Tüm siparişleri listeleme
    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // UPDATE: Siparişi güncelleme
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable UUID id, @RequestBody OrderDto orderDto) {
        OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllByStatus/{status}")
    public ResponseEntity<List<OrderDto> >getOrderByStatus(@PathVariable("status") OrderStatusEnum orderStatusEnum){
        return ResponseEntity.ok(orderService.getOrderByStatus(orderStatusEnum));
    }

    @GetMapping("/getAllByPrice/{min}/{max}")
    public ResponseEntity<List<OrderDto>>getOrderByPrice(
            @RequestParam Double min ,
            @RequestParam Double max){
        return ResponseEntity.ok(orderService.getOrderByTotalPrice(min,max));
    }


    // DELETE: Siparişi silme
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        try {
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

