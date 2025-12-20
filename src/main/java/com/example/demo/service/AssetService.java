package com.example.demo.service;

import com.example.demo.entity.Asset;
import java.util.List;

public interface AssetService {

    Asset createAsset(Asset asset);

    List<Asset> getAllAssets();

    Asset getAsset(Long id);

    Asset updateStatus(Long id, String status);
}
