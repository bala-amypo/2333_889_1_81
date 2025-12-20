package com.example.demo.service;

import com.example.demo.dto.AssetRequest;
import com.example.demo.entity.Asset;

import java.util.List;

public interface AssetService {

    Asset createAsset(AssetRequest request);

    List<Asset> getAllAssets();
}
