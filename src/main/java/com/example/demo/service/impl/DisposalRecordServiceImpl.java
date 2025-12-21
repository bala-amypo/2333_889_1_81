package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DisposalRecord;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DisposalRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DisposalRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DisposalRecordServiceImpl implements DisposalRecordService {
    
    private final DisposalRecordRepository disposalRecordRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;
    
    public DisposalRecordServiceImpl(DisposalRecordRepository disposalRecordRepository,
                                     AssetRepository assetRepository,
                                     UserRepository userRepository) {
        this.disposalRecordRepository = disposalRecordRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public DisposalRecord createDisposal(Long assetId, DisposalRecord disposal) {
        // Null checks
        if (assetId == null) {
            throw new ValidationException("Asset ID cannot be null");
        }
        if (disposal == null) {
            throw new ValidationException("Disposal record cannot be null");
        }
        if (disposal.getApprovedBy() == null || disposal.getApprovedBy().getId() == null) {
            throw new ValidationException("Approver information is required");
        }
        if (disposal.getDisposalMethod() == null || disposal.getDisposalMethod().trim().isEmpty()) {
            throw new ValidationException("Disposal method is required");
        }
        if (disposal.getDisposalDate() == null) {
            throw new ValidationException("Disposal date is required");
        }
        
        // Fetch asset
        Asset asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        
        // Fetch approver
        User approver = userRepository.findById(disposal.getApprovedBy().getId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Validate approver is ADMIN
        if (!"ADMIN".equals(approver.getRole())) {
            throw new ValidationException("Approver must be ADMIN");
        }
        
        // Validate disposal date not in future
        if (disposal.getDisposalDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Disposal date cannot be in the future");
        }
        
        try {
            // Set relationships
            disposal.setAsset(asset);
            disposal.setApprovedBy(approver);
            
            // Save disposal record
            DisposalRecord saved = disposalRecordRepository.save(disposal);
            
            // CRITICAL: Update asset status to DISPOSED
            asset.setStatus("DISPOSED");
            assetRepository.save(asset);
            
            return saved;
        } catch (Exception e) {
            throw new ValidationException("Failed to create disposal record: " + e.getMessage());
        }
    }
    
    @Override
    public DisposalRecord getDisposal(Long id) {
        if (id == null) {
            throw new ValidationException("Disposal ID cannot be null");
        }
        return disposalRecordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Disposal record not found"));
    }
    
    @Override
    public List<DisposalRecord> getAllDisposals() {
        try {
            return disposalRecordRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve disposals: " + e.getMessage());
        }
    }
}