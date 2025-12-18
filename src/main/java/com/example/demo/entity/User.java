package com.example.demo.entity;
import java.time.LocalDateTime;
import jakarta.persistence.Id;
public class User{
    @Id
    private Long id;
    private String fullName;
    private String email;
    private String department;
    private String ADMIN;
    private String USER;
    private String password;
    private LocalDateTime createAt=LocalDateTime.now();
    

    
    
    
    
}