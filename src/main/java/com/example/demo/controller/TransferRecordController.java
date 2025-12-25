package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
@Tag(name = "Transfers", description = "Transfer record management endpoints")
public class TransferRecordController {
    
    private final TransferRecordService transferRecordService;
    
    public TransferRecordController(TransferRecordService transferRecordService) {
        this.transferRecordService = transferRecordService;
        }

@PostMapping("/{assetId}")
public ResponseEntity<TransferRecord> createTransfer(@PathVariable Long assetId,
                                                     @RequestBody TransferRecord record) {
    TransferRecord created = transferRecordService.createTransfer(assetId, record);
    return ResponseEntity.ok(created);
}

@GetMapping("/asset/{assetId}")
public ResponseEntity<List<TransferRecord>> getTransfersForAsset(@PathVariable Long assetId) {
    List<TransferRecord> transfers = transferRecordService.getTransfersForAsset(assetId);
    return ResponseEntity.ok(transfers);
}

@GetMapping("/{id}")
public ResponseEntity<TransferRecord> getTransfer(@PathVariable Long id) {
    TransferRecord transfer = transferRecordService.getTransfer(id);
    return ResponseEntity.ok(transfer);
}
}