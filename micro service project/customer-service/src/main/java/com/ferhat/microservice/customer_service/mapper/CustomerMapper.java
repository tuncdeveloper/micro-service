package com.ferhat.microservice.customer_service.mapper;

import com.ferhat.microservice.customer_service.dto.CustomerDto;
import com.ferhat.microservice.customer_service.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CustomerMapper extends BaseMapper<Customer, CustomerDto>{

    CustomerDto mapToDto(Customer customer);
    Customer mapToEntity(CustomerDto customerDto);
    List<CustomerDto> mapToDtoList(List<Customer> customers);
    List<Customer> mapToEntityList(List<CustomerDto> customerDtos);


}
