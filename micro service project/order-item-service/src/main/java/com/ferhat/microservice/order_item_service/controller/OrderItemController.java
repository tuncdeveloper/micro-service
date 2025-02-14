package com.ferhat.microservice.order_item_service.controller;


import com.ferhat.microservice.order_item_service.dto.OrderItemDto;
import com.ferhat.microservice.order_item_service.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orderItems")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    // CREATE: Yeni sipariş kalemi ekleme
    @PostMapping("/create")
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItemDto createdOrderItem = orderItemService.createOrderItem(orderItemDto);
      return  ResponseEntity.ok(createdOrderItem);
    }

    // READ: Belirli bir OrderItem getir
    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable UUID id) {
        OrderItemDto orderItemDto = orderItemService.getOrderItemById(id);
        return  ResponseEntity.ok(orderItemDto);
    }

    // READ: Tüm sipariş kalemlerini getir
    @GetMapping("/getAll")
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        List<OrderItemDto> orderItemDtos = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItemDtos);
    }

    // UPDATE: Sipariş kalemini güncelle
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable UUID id, @RequestBody OrderItemDto orderItemDto) {
        OrderItemDto updatedProduct = orderItemService.updateOrderItem(id, orderItemDto);
        return ResponseEntity.ok(updatedProduct);
    }

    // READ: Belirli miktara sahip tüm sipariş kalemlerini getir
    @GetMapping("/getByQuantity/{quantity}")
    public ResponseEntity<List<OrderItemDto>> getAllOrderItemByQuantity(@PathVariable Integer quantity) {
        List<OrderItemDto> orderItemDtos = orderItemService.getAllOrderItemByQuantity(quantity);
        return ResponseEntity.ok(orderItemDtos);
    }

    // DELETE: Sipariş kalemini sil
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable UUID id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build(); // 204 No Content response
    }
}

