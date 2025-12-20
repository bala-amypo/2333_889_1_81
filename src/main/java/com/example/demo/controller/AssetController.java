package com.example.demo.controller;

import com.example.demo.dto.AssetStatusUpdateRequest;
import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Assets", description = "Asset management endpoints")
public class AssetController {
    
    private final AssetService assetService;
    
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }
    
    @PostMapping
    @Operation(summary = "Create asset", description = "Creates a new asset in the system")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset created = assetService.createAsset(asset);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping
    @Operation(summary = "Get all assets", description = "Retrieves all assets in the system")
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get asset by ID", description = "Retrieves a specific asset by its ID")
    public ResponseEntity<Asset> getAsset(@PathVariable Long id) {
        Asset asset = assetService.getAsset(id);
        return ResponseEntity.ok(asset);
    }
    
    @PutMapping("/status/{id}")
    @Operation(summary = "Update asset status", description = "Updates the status of an asset")
    public ResponseEntity<Asset> updateStatus(@PathVariable Long id, 
                                              @RequestBody AssetStatusUpdateRequest request) {
        Asset updated = assetService.updateStatus(id, request.getStatus());
        return ResponseEntity.ok(updated);
    }
}