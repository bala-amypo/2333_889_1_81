package com.example.demo.service.impl;

import com.example.demo.dto.AssetRequest;
import com.example.demo.entity.Asset;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public AssetServiceImpl(AssetRepository assetRepository,
                            UserRepository userRepository) {
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Asset createAsset(AssetRequest request) {

        User user = userRepository.findById(request.getCurrentHolderId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Asset asset = new Asset();
        asset.setAssetTag(request.getAssetTag());
        asset.setAssetType(request.getAssetType());
        asset.setModel(request.getModel());
        asset.setPurchaseDate(request.getPurchaseDate());
        asset.setStatus(request.getStatus());
        asset.setCurrentHolder(user);

        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
}
