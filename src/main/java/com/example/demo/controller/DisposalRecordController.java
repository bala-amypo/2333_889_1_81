package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class DisposalRecordController {

    @Autowired
    private DisposalRecordService disposalRecordService;

    @PostMapping
    public ResponseEntity<DisposalRecord> createDisposal(@RequestBody DisposalRecord record) {
        Long assetId = record.getAsset() != null ? record.getAsset().getId() : null;
        return ResponseEntity.ok(disposalRecordService.createDisposal(assetId, record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalRecord> getDisposal(@PathVariable Long id) {
        return ResponseEntity.ok(disposalRecordService.getDisposal(id));
    }
}