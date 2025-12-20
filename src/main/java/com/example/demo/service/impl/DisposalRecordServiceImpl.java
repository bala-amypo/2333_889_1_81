package com.example.demo.service.impl;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DisposalRecordRepository;
import com.example.demo.service.DisposalRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

        record.setDisposalDate(LocalDate.now());
        return disposalRepo.save(record);
    }
}
