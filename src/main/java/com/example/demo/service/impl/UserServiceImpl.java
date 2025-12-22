package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    @Override
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already exists");
        }

        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }

        if (user.getDepartment() == null) {
            throw new ValidationException("Department is required");
        }

        return userRepository.save(user);
    }

    // UPDATE
    @Override
    public User updateUser(Long id, User updatedUser) {

        User existing = getUser(id);

        existing.setFullName(updatedUser.getFullName());
        existing.setDepartment(updatedUser.getDepartment());
        existing.setRole(updatedUser.getRole());

        return userRepository.save(existing);
    }

    // READ BY ID
    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + id));
    }

    // READ ALL
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
