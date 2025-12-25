package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get all users.
     * Useful for admin dashboards or verifying user creation in tests.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get a specific user by ID.
     * Maps to GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get user profile by Email.
     * Maps to GET /api/users/profile?email={email}
     */
    @GetMapping("/profile")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}