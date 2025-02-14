package com.ferhat.microservice.auth_service.repository;

import com.ferhat.microservice.auth_service.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, UUID> {
    Optional<AuthenticationToken> findByToken(String token);
    Optional<AuthenticationToken> findByCustomerId(UUID customerId);
}
