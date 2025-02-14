package com.ferhat.microservice.customer_service.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "customers")
@Entity
@Builder
public class Customer{

    @Id
    @GeneratedValue()
    private UUID customerId;  // Primary Key (UUID)

    @Column(nullable = false)
    private String name;  // Müşteri adı

    @Column(unique = true, nullable = false)
    private String email;  // Benzersiz email

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;  // Şifre hash'i

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;  // Kayıt oluşturulma tarihi

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // Kayıt güncelleme tarihi


}
