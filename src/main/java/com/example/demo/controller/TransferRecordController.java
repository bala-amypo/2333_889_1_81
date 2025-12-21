package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.exception.ValidationException;
import com.example.demo.service.TransferRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Transfers", description = "Asset transfer record endpoints")
public class TransferRecordController {
    
    private final TransferRecordService transferRecordService;
    
    public TransferRecordController(TransferRecordService transferRecordService) {
        this.transferRecordService = transferRecordService;
    }
    
    @PostMapping("/{assetId}")
    @Operation(summary = "Create transfer", description = "Creates a transfer record (requires ADMIN)")
    public ResponseEntity<TransferRecord> createTransfer(@PathVariable Long assetId,
                                                        @RequestBody TransferRecord record) {
        if (assetId == null) {
            throw new ValidationException("Asset ID cannot be null");
        }
        if (record == null) {
            throw new ValidationException("Transfer record cannot be null");
        }
        TransferRecord created = transferRecordService.createTransfer(assetId, record);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/asset/{assetId}")
    @Operation(summary = "Get transfers for asset", description = "Retrieves all transfer records for a specific asset")
    public ResponseEntity<List<TransferRecord>> getTransfersForAsset(@PathVariable Long assetId) {
        if (assetId == null) {
            throw new ValidationException("Asset ID cannot be null");
        }
        List<TransferRecord> transfers = transferRecordService.getTransfersForAsset(assetId);
        return ResponseEntity.ok(transfers);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get transfer by ID", description = "Retrieves a specific transfer record by ID")
    public ResponseEntity<TransferRecord> getTransfer(@PathVariable Long id) {
        if (id == null) {
            throw new ValidationException("Transfer ID cannot be null");
        }
        TransferRecord transfer = transferRecordService.getTransfer(id);
        return ResponseEntity.ok(transfer);
    }
}