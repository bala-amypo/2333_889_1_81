package com.example.demo.service;

import com.example.demo.entity.TransferRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.TransferRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        record.setTransferDate(LocalDateTime.now());
        return transferRepo.save(record);
    }

    @Override
    public List<TransferRecord> getTransfersByAsset(Long assetId) {
        return transferRepo.findByAssetId(assetId);
    }
}
