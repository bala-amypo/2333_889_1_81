package com.example.demo.controller;

import com.example.demo.dto.AssetRequest;
import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    // ✅ FIXED POST
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Asset createAsset(@RequestBody AssetRequest request) {
        return assetService.createAsset(request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }
}
