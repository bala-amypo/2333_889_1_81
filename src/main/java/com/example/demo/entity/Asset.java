package com.example.demo.entity;
public class Asset{
    @Id
    private Long id;
    @Column(unique=true)
    private String assetTag;
    private String LAPTOP;
    private String DESKTOP;
    private String PRINTER;
    private String NETWORK DEVICE;
    private String OTHER;
    private String model;
    private LocalDate purchaseDate;
    private String AVAILABLE;
    private String ASSIGNED;
    private String IN_REPAIR;
    private String TRANSFERRED;
    private String DISPOSED;
    private User curentHolder;
    private LocalDateTime createAt=LocalDateTime.now();

}