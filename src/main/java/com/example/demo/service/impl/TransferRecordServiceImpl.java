package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.TransferRecord;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransferRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferRecordServiceImpl implements TransferRecordService {

    @Autowired
    private TransferRecordRepository transferRecordRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TransferRecord createTransfer(Long assetId, TransferRecord record) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        
        User approver = userRepository.findById(record.getApprovedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Approver not found"));

        if (record.getTransferDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Transfer date cannot be in the future");
        }

        record.setAsset(asset);
        record.setApprovedBy(approver);
        return transferRecordRepository.save(record);
    }

    @Override
    public List<TransferRecord> getTransfersForAsset(Long assetId) {
        return transferRecordRepository.findByAsset_Id(assetId);
    }
}