package com.example.demo.service;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DisposalRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DisposalRecordServiceImpl implements DisposalRecordService {

    private final DisposalRecordRepository disposalRepo;
    private final AssetRepository assetRepo;

    public DisposalRecordServiceImpl(DisposalRecordRepository disposalRepo,
                                     AssetRepository assetRepo) {
        this.disposalRepo = disposalRepo;
        this.assetRepo = assetRepo;
    }

    @Override
    public DisposalRecord createDisposal(DisposalRecord record) {

        assetRepo.findById(record.getAsset().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        record.setDisposalDate(LocalDateTime.now());
        return disposalRepo.save(record);
    }
}
