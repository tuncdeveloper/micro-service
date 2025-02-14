package com.ferhat.microservice.payment_service.mapper;

import com.ferhat.microservice.payment_service.dto.PaymentDto;
import com.ferhat.microservice.payment_service.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface PaymentMapper extends BaseMapper<Payment, PaymentDto>{

    Payment mapToEntity(PaymentDto paymentDto);
    PaymentDto mapToDto(Payment Entity);

    List<PaymentDto> mapToDtoList(List<Payment> list);
    List<Payment> mapToEntityList(List<PaymentDto> dtoList);

}
