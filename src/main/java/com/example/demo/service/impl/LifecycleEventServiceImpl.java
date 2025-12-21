package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.LifecycleEvent;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.LifecycleEventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LifecycleEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LifecycleEventServiceImpl implements LifecycleEventService {
    
    private final LifecycleEventRepository lifecycleEventRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;
    
    public LifecycleEventServiceImpl(LifecycleEventRepository lifecycleEventRepository,
                                     AssetRepository assetRepository,
                                     UserRepository userRepository) {
        this.lifecycleEventRepository = lifecycleEventRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public LifecycleEvent logEvent(Long assetId, Long userId, LifecycleEvent event) {
        // Null checks
        if (assetId == null) {
            throw new ValidationException("Asset ID cannot be null");
        }
        if (userId == null) {
            throw new ValidationException("User ID cannot be null");
        }
        if (event == null) {
            throw new ValidationException("Event data cannot be null");
        }
        
        // Fetch asset
        Asset asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        
        // Fetch user
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Validate eventType
        if (event.getEventType() == null || event.getEventType().trim().isEmpty()) {
            throw new ValidationException("Event type is required");
        }
        
        // Validate eventDescription
        if (event.getEventDescription() == null || event.getEventDescription().trim().isEmpty()) {
            throw new ValidationException("Event description cannot be empty");
        }
        
        try {
            // Set relationships
            event.setAsset(asset);
            event.setPerformedBy(user);
            
            // Save and return
            return lifecycleEventRepository.save(event);
        } catch (Exception e) {
            throw new ValidationException("Failed to log event: " + e.getMessage());
        }
    }
    
    @Override
    public List<LifecycleEvent> getEventsForAsset(Long assetId) {
        if (assetId == null) {
            throw new ValidationException("Asset ID cannot be null");
        }
        try {
            return lifecycleEventRepository.findByAssetId(assetId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve events: " + e.getMessage());
        }
    }
    
    @Override
    public LifecycleEvent getEvent(Long id) {
        if (id == null) {
            throw new ValidationException("Event ID cannot be null");
        }
        return lifecycleEventRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Lifecycle event not found"));
    }
}