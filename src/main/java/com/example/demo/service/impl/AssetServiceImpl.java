package com.example.demo.service.impl;

import com.example.demo.dto.AssetStatusUpdateRequest;
import com.example.demo.entity.Asset;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepo;
    private final UserRepository userRepo;

    public AssetServiceImpl(AssetRepository assetRepo, UserRepository userRepo) {
        this.assetRepo = assetRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Asset createAsset(AssetStatusUpdateRequest request) {
        Asset asset = new Asset();
        mapRequestToAsset(asset, request);
        return assetRepo.save(asset);
    }

    @Override
    public Asset updateAsset(Long id, AssetStatusUpdateRequest request) {
        Asset asset = assetRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        mapRequestToAsset(asset, request);
        return assetRepo.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepo.findAll();
    }

    // 🔹 COMMON MAPPING LOGIC
    private void mapRequestToAsset(Asset asset, AssetStatusUpdateRequest request) {
        asset.setAssetTag(request.getAssetTag());
        asset.setAssetType(request.getAssetType());
        asset.setModel(request.getModel());
        asset.setStatus(request.getStatus());
        asset.setPurchaseDate(LocalDate.parse(request.getPurchaseDate()));

        if (request.getCurrentHolderId() != null) {
            User user = userRepo.findById(request.getCurrentHolderId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            asset.setCurrentHolder(user);
        }
    }
}
