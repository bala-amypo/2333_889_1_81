package com.example.demo.service.impl;

import com.example.demo.entity.TransferRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.service.TransferRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferRecordServiceImpl implements TransferRecordService {

    private final TransferRecordRepository transferRepo;
    private final AssetRepository assetRepo;

    public TransferRecordServiceImpl(TransferRecordRepository transferRepo,
                                     AssetRepository assetRepo) {
        this.transferRepo = transferRepo;
        this.assetRepo = assetRepo;
    }

    @Override
    public TransferRecord createTransfer(TransferRecord record) {

        assetRepo.findById(record.getAsset().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        record.setTransferDate(LocalDate.now());
        return transferRepo.save(record);
    }

    @Override
    public List<TransferRecord> getTransfersByAsset(Long assetId) {
        return transferRepo.findByAssetId(assetId);
    }
}
