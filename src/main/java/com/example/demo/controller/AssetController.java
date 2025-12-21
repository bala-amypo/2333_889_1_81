package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@Tag(name = "Asset Controller")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    // CREATE
    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return assetService.createAsset(asset);
    }

    // READ ALL
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Asset getAsset(@PathVariable Long id) {
        return assetService.getAsset(id);
    }

    // UPDATE STATUS
    @PutMapping("/status/{id}")
    public Asset updateStatus(@PathVariable Long id,
                              @RequestParam String status) {
        return assetService.updateStatus(id, status);
    }
}
