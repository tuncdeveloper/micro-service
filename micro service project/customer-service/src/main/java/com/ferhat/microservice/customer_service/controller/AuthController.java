package com.ferhat.microservice.customer_service.controller;

import com.ferhat.microservice.customer_service.dto.CustomerDto;
import com.ferhat.microservice.customer_service.exception.CustomerNotFoundException;
import com.ferhat.microservice.customer_service.exception.InvalidCustomerDataException;
import com.ferhat.microservice.customer_service.mapper.CustomerMapper;
import com.ferhat.microservice.customer_service.model.Customer;
import com.ferhat.microservice.customer_service.repository.CustomerRepository;
import com.ferhat.microservice.customer_service.service.AuthService;
import com.ferhat.microservice.customer_service.service.CustomerService;
import com.ferhat.microservice.customer_service.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {


   private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto registeredCustomer = authService.registerCustomer(customerDto);
        return ResponseEntity.ok(registeredCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestParam String email, @RequestParam String password) {
        String token = authService.loginCustomer(email, password);
        return ResponseEntity.ok(token);
    }


}
