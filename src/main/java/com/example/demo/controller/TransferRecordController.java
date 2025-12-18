package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferRecordController {

    private final TransferRecordService transferService;

    public TransferRecordController(TransferRecordService transferService) {
        this.transferService = transferService;
    }

    // Create transfer record
    @PostMapping
    public TransferRecord createTransfer(@RequestBody TransferRecord record) {
        return transferService.createTransfer(record);
    }

    // Get transfers by asset
    @GetMapping("/asset/{assetId}")
    public List<TransferRecord> getTransfersByAsset(@PathVariable Long assetId) {
        return transferService.getTransfersByAsset(assetId);
    }
}
