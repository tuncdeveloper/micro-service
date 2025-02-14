package com.ferhat.microservice.payment_service.service;

import com.ferhat.microservice.payment_service.dto.PaymentDto;
import com.ferhat.microservice.payment_service.exception.PaymentNotFoundException;
import com.ferhat.microservice.payment_service.mapper.PaymentMapper;
import com.ferhat.microservice.payment_service.model.Payment;
import com.ferhat.microservice.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;



    public PaymentService(PaymentRepository paymentRepository,PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.mapToEntity(paymentDto);
        payment = paymentRepository.save(payment);
        return paymentMapper.mapToDto(payment);
    }

    public PaymentDto getPaymentById(UUID id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
        return paymentMapper.mapToDto(payment);
    }

    public List<PaymentDto> getAllPayments( ) {
        List<Payment> payments = paymentRepository.findAll();
        return paymentMapper.mapToDtoList(payments);
    }

    public PaymentDto updatePayment(UUID id, PaymentDto paymentDto) {
        Optional<Payment> existingPaymentOpt = paymentRepository.findById(id);
        if (existingPaymentOpt.isPresent()) {
            Payment existingPayment = existingPaymentOpt.get();
            existingPayment.setPaymentId(paymentDto.getId());
            existingPayment.setOrderId(paymentDto.getOrderId());
            existingPayment.setTransactionId(paymentDto.getTransactionId());
            existingPayment.setPaymentStatusEnum(paymentDto.getPaymentStatusEnum());
            existingPayment.setPaymentMethodEnum(paymentDto.getPaymentMethodEnum());
            existingPayment.setUpdatedAt(paymentDto.getUpdatedAt());
            existingPayment.setCreatedAt(paymentDto.getCreatedAt());
            existingPayment = paymentRepository.save(existingPayment);
            return paymentMapper.mapToDto(existingPayment);
        }
        return null;
    }

    public void deletePayment(UUID id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
        paymentRepository.delete(payment);
    }


}
