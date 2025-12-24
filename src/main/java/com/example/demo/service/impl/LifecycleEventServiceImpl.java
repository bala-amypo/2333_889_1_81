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
    
    // Constructor injection - EXACT ORDER REQUIRED FOR TESTS
    public LifecycleEventServiceImpl(LifecycleEventRepository lifecycleEventRepository,
                                     AssetRepository assetRepository,
                                     UserRepository userRepository) {
        this.lifecycleEventRepository = lifecycleEventRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public LifecycleEvent logEvent(Long assetId, Long userId, LifecycleEvent event) {
        // Validate asset exists
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id: " + assetId));
        
        // Validate user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        
        // Validate event type
        if (event.getEventType() == null || event.getEventType().trim().isEmpty()) {
            throw new ValidationException("Event type is required");
        }
        
        // Validate event description
        if (event.getEventDescription() == null || event.getEventDescription().trim().isEmpty()) {
            throw new ValidationException("Event description is required");
        }
        
        // Set relationships
        event.setAsset(asset);
        event.setPerformedBy(user);
        
        return lifecycleEventRepository.save(event);
    }
    
    @Override
    public List<LifecycleEvent> getEventsForAsset(Long assetId) {
        return lifecycleEventRepository.findByAsset_Id(assetId);
    }
    
    @Override
    public LifecycleEvent getEvent(Long id) {
        return lifecycleEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lifecycle event not found with id: " + id));
    }
}