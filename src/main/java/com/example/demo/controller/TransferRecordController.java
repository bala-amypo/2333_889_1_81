package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferRecordController {

    @Autowired
    private TransferRecordService transferRecordService;

    @PostMapping
    public ResponseEntity<?> createTransfer(@RequestBody TransferRecord record) {
        Long assetId = record.getAsset() != null ? record.getAsset().getId() : null;
        return ResponseEntity.ok(transferRecordService.createTransfer(assetId, record));
    }

    @GetMapping("/asset/{assetId}")
    public List<TransferRecord> getTransfers(@PathVariable Long assetId) {
        return transferRecordService.getTransfersForAsset(assetId);
    }
}