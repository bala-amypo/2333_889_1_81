package com.example.demo.controller;

import com.example.demo.dto.AssetStatusUpdateRequest;
import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    // Create Asset
    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return assetService.createAsset(asset);
    }

    // Get all assets
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    // Get asset by id
    @GetMapping("/{id}")
    public Asset getAsset(@PathVariable Long id) {
        return assetService.getAsset(id);
    }

    // Update asset status
    @PutMapping("/status/{id}")
    public Asset updateStatus(@PathVariable Long id,
                              @RequestBody AssetStatusUpdateRequest request) {
        return assetService.updateStatus(id, request.getStatus());
    }
}
