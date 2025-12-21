package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    
    private final AssetRepository assetRepository;
    
    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }
    
    @Override
    public Asset createAsset(Asset asset) {
        if (asset == null) {
            throw new ValidationException("Asset data cannot be null");
        }
        if (asset.getAssetTag() == null || asset.getAssetTag().trim().isEmpty()) {
            throw new ValidationException("Asset tag is required");
        }
        if (asset.getAssetType() == null || asset.getAssetType().trim().isEmpty()) {
            throw new ValidationException("Asset type is required");
        }
        
        try {
            return assetRepository.save(asset);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
                throw new ValidationException("Asset tag already exists");
            }
            throw new ValidationException("Failed to create asset: " + e.getMessage());
        }
    }
    
    @Override
    public Asset getAsset(Long id) {
        if (id == null) {
            throw new ValidationException("Asset ID cannot be null");
        }
        return assetRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }
    
    @Override
    public List<Asset> getAllAssets() {
        try {
            return assetRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve assets: " + e.getMessage());
        }
    }
    
    @Override
    public Asset updateStatus(Long assetId, String status) {
        if (assetId == null) {
            throw new ValidationException("Asset ID cannot be null");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new ValidationException("Status cannot be null or empty");
        }
        
        Asset asset = getAsset(assetId);
        
        try {
            asset.setStatus(status);
            return assetRepository.save(asset);
        } catch (Exception e) {
            throw new ValidationException("Failed to update asset status: " + e.getMessage());
        }
    }
}