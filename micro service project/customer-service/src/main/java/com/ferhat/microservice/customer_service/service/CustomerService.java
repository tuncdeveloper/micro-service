package com.ferhat.microservice.customer_service.service;


import com.ferhat.microservice.customer_service.dto.CustomerDto;
import com.ferhat.microservice.customer_service.exception.CustomerNotFoundException;
import com.ferhat.microservice.customer_service.exception.InvalidCustomerDataException;
import com.ferhat.microservice.customer_service.mapper.CustomerMapper;
import com.ferhat.microservice.customer_service.model.Customer;
import com.ferhat.microservice.customer_service.repository.CustomerRepository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Primary
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerService(CustomerRepository customerRepository,CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    // Müşteri oluşturma
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.mapToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.mapToDto(savedCustomer);
    }

    // Tüm müşterileri listeleme
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.mapToDtoList(customers);
    }

    // Müşteri ID'ye göre arama
    public CustomerDto getCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        return customerMapper.mapToDto(customer);
    }

    // Müşteri adına göre arama
    public CustomerDto getCustomerByName(String name) {
        Customer customer = customerRepository.findByName(name)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with name: " + name));
        return customerMapper.mapToDto(customer);
    }

    // Müşteri güncelleme
    @Transactional
    public CustomerDto updateCustomer(UUID id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new InvalidCustomerDataException("Customer not found with id: " + id));

        // Verilerin doğruluğunu kontrol et
        if (customerDto.getName() == null || customerDto.getName().isEmpty()) {
            throw new InvalidCustomerDataException("Customer name cannot be null or empty");
        }

        customer.setCustomerId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setUpdatedAt(customerDto.getUpdatedAt());
        customer.setCreatedAt(customerDto.getCreatedAt());
        customer.setPasswordHash(customerDto.getPasswordHash());

        customer = customerRepository.save(customer);
        return customerMapper.mapToDto(customer);
    }

    // Müşteri silme
    @Transactional
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }


}