package com.ferhat.microservice.payment_service.controller;


import com.ferhat.microservice.payment_service.dto.PaymentDto;
import com.ferhat.microservice.payment_service.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Create Payment
    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        PaymentDto createdPayment = paymentService.createPayment(paymentDto);
        return ResponseEntity.ok(createdPayment);
    }

    // Get Payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable UUID id) {
        PaymentDto paymentDto = paymentService.getPaymentById(id);
        return ResponseEntity.ok(paymentDto);
    }

    // Get All Payments
    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        List<PaymentDto> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // Update Payment
    @PutMapping("/update/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable UUID id, @RequestBody PaymentDto paymentDto) {
        PaymentDto updatePayment = paymentService.updatePayment(id, paymentDto);
        return ResponseEntity.ok(updatePayment);
    }

    // Delete Payment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable UUID id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build(); // 204 No Content response
    }
}

