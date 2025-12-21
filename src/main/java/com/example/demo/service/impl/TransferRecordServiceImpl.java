package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.TransferRecord;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransferRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferRecordServiceImpl implements TransferRecordService {
    
    private final TransferRecordRepository transferRecordRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;
    
    public TransferRecordServiceImpl(TransferRecordRepository transferRecordRepository,
                                     AssetRepository assetRepository,
                                     UserRepository userRepository) {
        this.transferRecordRepository = transferRecordRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public TransferRecord createTransfer(Long assetId, TransferRecord record) {
        // Null checks
        if (assetId == null) {
            throw new ValidationException("Asset ID cannot be null");
        }
        if (record == null) {
            throw new ValidationException("Transfer record cannot be null");
        }
        if (record.getApprovedBy() == null || record.getApprovedBy().getId() == null) {
            throw new ValidationException("Approver information is required");
        }
        if (record.getFromDepartment() == null || record.getFromDepartment().trim().isEmpty()) {
            throw new ValidationException("From department is required");
        }
        if (record.getToDepartment() == null || record.getToDepartment().trim().isEmpty()) {
            throw new ValidationException("To department is required");
        }
        if (record.getTransferDate() == null) {
            throw new ValidationException("Transfer date is required");
        }
        
        // Fetch asset
        Asset asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        
        // Fetch approver
        User approver = userRepository.findById(record.getApprovedBy().getId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Validate approver is ADMIN
        if (!"ADMIN".equals(approver.getRole())) {
            throw new ValidationException("Approver must be ADMIN");
        }
        
        // Validate departments differ
        if (record.getFromDepartment().equals(record.getToDepartment())) {
            throw new ValidationException("From department must differ from to department");
        }
        
        // Validate transfer date not in future
        if (record.getTransferDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Transfer date cannot be in the future");
        }
        
        try {
            // Set relationships
            record.setAsset(asset);
            record.setApprovedBy(approver);
            
            // Save and return
            return transferRecordRepository.save(record);
        } catch (Exception e) {
            throw new ValidationException("Failed to create transfer record: " + e.getMessage());
        }
    }
    
    @Override
    public List<TransferRecord> getTransfersForAsset(Long assetId) {
        if (assetId == null) {
            throw new ValidationException("Asset ID cannot be null");
        }
        try {
            return transferRecordRepository.findByAssetId(assetId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve transfers: " + e.getMessage());
        }
    }
    
    @Override
    public TransferRecord getTransfer(Long id) {
        if (id == null) {
            throw new ValidationException("Transfer ID cannot be null");
        }
        return transferRecordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transfer record not found"));
    }
}