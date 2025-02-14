package com.ferhat.microservice.auth_service.controller;


import com.ferhat.microservice.auth_service.service.AuthenticationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationTokenController {

    @Autowired
    private AuthenticationTokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestParam UUID customerId) {
        String token = tokenService.generateToken(customerId);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        boolean isValid = tokenService.validateToken(token);
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/customer")
    public ResponseEntity<UUID> getCustomerIdFromToken(@RequestParam String token) {
        UUID customerId = tokenService.getCustomerIdFromToken(token);
        return ResponseEntity.ok(customerId);
    }
}