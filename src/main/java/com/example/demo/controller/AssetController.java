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

    // ✅ CREATE asset
    @PostMapping
    public Asset createAsset(@RequestBody AssetStatusUpdateRequest request) {
        return assetService.createAsset(request);
    }

    // ✅ GET all assets
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    // ✅ UPDATE asset (PUT)
    @PutMapping("/{id}")
    public Asset updateAsset(
            @PathVariable Long id,
            @RequestBody AssetStatusUpdateRequest request
    ) {
        return assetService.updateAsset(id, request);
    }
}
