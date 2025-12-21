package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public User registerUser(User user) {
        // Null checks
        if (user == null) {
            throw new ValidationException("User data cannot be null");
        }
        
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        
        // Validation: email uniqueness
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already in use");
        }
        
        // Validation: password
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        
        if (user.getPassword().length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }
        
        // Validation: department required
        if (user.getDepartment() == null || user.getDepartment().trim().isEmpty()) {
            throw new ValidationException("Department is required");
        }
        
        // Validation: fullName required
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            throw new ValidationException("Full name is required");
        }
        
        try {
            // Set default role if not provided
            if (user.getRole() == null || user.getRole().trim().isEmpty()) {
                user.setRole("USER");
            }
            
            // Encode password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            // Save and return
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ValidationException("Failed to register user: " + e.getMessage());
        }
    }
    
    @Override
    public User getUser(Long id) {
        if (id == null) {
            throw new ValidationException("User ID cannot be null");
        }
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    
    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve users: " + e.getMessage());
        }
    }
}