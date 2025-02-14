package com.ferhat.microservice.customer_service.service;

import com.ferhat.microservice.customer_service.model.Customer;
import com.ferhat.microservice.customer_service.model.CustomerUserDetails;
import com.ferhat.microservice.customer_service.repository.CustomerRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found with name: " + name));

        return new CustomerUserDetails(customer);
    }
}
