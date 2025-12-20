package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepo;

    public AssetServiceImpl(AssetRepository assetRepo) {
        this.assetRepo = assetRepo;
    }

    @Override
    public Asset createAsset(Asset asset) {
        return assetRepo.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepo.findAll();
    }

    @Override
    public Asset getAsset(Long id) {
        return assetRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }

    @Override
    public Asset updateStatus(Long id, String status) {
        Asset asset = getAsset(id);
        asset.setStatus(status);
        return assetRepo.save(asset);
    }
}
