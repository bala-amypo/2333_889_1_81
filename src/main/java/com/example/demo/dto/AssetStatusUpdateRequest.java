package com.example.demo.dto;

public class AssetStatusUpdateRequest {

    private String assetTag;
    private String assetType;
    private String model;
    private String purchaseDate;
    private String status;
    private Long currentHolderId;

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCurrentHolderId() {
        return currentHolderId;
    }

    public void setCurrentHolderId(Long currentHolderId) {
        this.currentHolderId = currentHolderId;
    }
}
