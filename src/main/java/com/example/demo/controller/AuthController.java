package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication endpoints for registration and login")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    
    public AuthController(AuthenticationManager authenticationManager, 
                         UserService userService, 
                         JwtUtil jwtUtil,
                         PasswordEncoder passwordEncoder,
                         UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account with default USER role")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // Validate request - PREVENTS 500 ERRORS
        if (request == null) {
            throw new ValidationException("Request body cannot be null");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (request.getFullName() == null || request.getFullName().trim().isEmpty()) {
            throw new ValidationException("Full name is required");
        }
        if (request.getDepartment() == null || request.getDepartment().trim().isEmpty()) {
            throw new ValidationException("Department is required");
        }
        
        // Create user
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setDepartment(request.getDepartment());
        user.setPassword(request.getPassword());
        
        User savedUser = userService.registerUser(user);
        
        // Return response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("userId", savedUser.getId());
        response.put("email", savedUser.getEmail());
        response.put("role", savedUser.getRole());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticate user and receive JWT token")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Validate request - PREVENTS 500 ERRORS
        if (request == null) {
            throw new ValidationException("Request body cannot be null");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        // Get user details
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new ValidationException("User not found"));
        
        // Generate JWT token
        String token = jwtUtil.generateTokenForUser(user);
        
        // Return response with token
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("userId", user.getId());
        response.put("email", user.getEmail());
        response.put("role", user.getRole());
        
        return ResponseEntity.ok(response);
    }
}