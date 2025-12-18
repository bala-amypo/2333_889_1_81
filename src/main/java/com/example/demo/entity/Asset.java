package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "assetTag"))
public class Asset {

    @Id
    @GeneratedValue
    private Long id;

    private String assetTag;
    private String assetType;
    private String model;
    private LocalDate purchaseDate;
    private String status;

    @ManyToOne
    private User currentHolder;

    private LocalDateTime createdAt;

    @PrePersist
    void init() {
        if (status == null) status = "AVAILABLE";
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    // getters & setters
}
