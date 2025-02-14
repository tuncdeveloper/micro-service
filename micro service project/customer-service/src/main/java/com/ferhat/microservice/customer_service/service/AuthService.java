package com.ferhat.microservice.customer_service.service;

import com.ferhat.microservice.customer_service.dto.CustomerDto;
import com.ferhat.microservice.customer_service.exception.CustomerNotFoundException;
import com.ferhat.microservice.customer_service.exception.InvalidCustomerDataException;
import com.ferhat.microservice.customer_service.mapper.CustomerMapper;
import com.ferhat.microservice.customer_service.model.Customer;
import com.ferhat.microservice.customer_service.repository.CustomerRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public AuthService(JwtService jwtService, AuthenticationManager authenticationManager, CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    @Transactional
    public CustomerDto registerCustomer(CustomerDto customerDto) {
        // Kullanıcı adının benzersiz olduğunu kontrol et
        if (customerRepository.findByName(customerDto.getName()).isPresent()) {
            throw new InvalidCustomerDataException("Customer with this name already exists: " + customerDto.getName());
        }

        // Email'in benzersiz olduğunu kontrol et
        if (customerRepository.findByEmail(customerDto.getEmail()).isPresent()) {
            throw new InvalidCustomerDataException("Customer with this email already exists: " + customerDto.getEmail());
        }

        // Şifreyi hash'le
        String hashedPassword = passwordEncoder.encode(customerDto.getPasswordHash());
        customerDto.setPasswordHash(hashedPassword);

        // CustomerDto'yu Customer entity'sine dönüştür ve kaydet
        Customer customer = customerMapper.mapToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);

        // Kaydedilen müşteriyi DTO'ya dönüştür ve döndür
        return customerMapper.mapToDto(savedCustomer);
    }

    // Login (Giriş) İşlemi
    public String loginCustomer(String email, String password) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        // Kullanıcıyı email'e göre bul
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with email: " + email));

        // Şifreyi doğrula
        if (!passwordEncoder.matches(password, customer.getPasswordHash())) {
            throw new InvalidCustomerDataException("Invalid password");
        }

        // JWT token oluştur ve döndür
        return jwtService.generateToken(customer.getName()); // Token'in subject'i olarak email kullanılıyor
    }

}
