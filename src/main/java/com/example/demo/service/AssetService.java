package com.example.demo.service;

import com.example.demo.dto.AssetStatusUpdateRequest;
import com.example.demo.entity.Asset;

import java.util.List;

public interface AssetService {

    Asset createAsset(AssetStatusUpdateRequest request);

    Asset updateAsset(Long id, AssetStatusUpdateRequest request);

    List<Asset> getAllAssets();
}
