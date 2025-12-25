package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    // Test t14: createAsset_success
    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset savedAsset = assetService.createAsset(asset);
        return new ResponseEntity<>(savedAsset, HttpStatus.CREATED);
    }

    // Test t17: getAllAssets_emptyList
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    // Test t15: getAssetById_found
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        Asset asset = assetService.getAsset(id);
        return ResponseEntity.ok(asset);
    }

    // Test t06 & t18: updateStatus
    // Note: The path structure matches test t06: "/api/assets/status/{id}"
    @PostMapping("/status/{id}")
    public ResponseEntity<Asset> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String status = request.get("status");
        Asset updatedAsset = assetService.updateStatus(id, status);
        return ResponseEntity.ok(updatedAsset);
    }
}