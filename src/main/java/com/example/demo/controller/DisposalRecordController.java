package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
@Tag(name = "Disposals", description = "Disposal record management endpoints")
public class DisposalRecordController {
    
    private final DisposalRecordService disposalRecordService;
    
    public DisposalRecordController(DisposalRecordService disposalRecordService) {
        this.disposalRecordService = disposalRecordService;
    }
    
    @PostMapping("/{assetId}")
    public ResponseEntity<DisposalRecord> createDisposal(@PathVariable Long assetId,
                                                         @RequestBody DisposalRecord disposal) {
        DisposalRecord created = disposalRecordService.createDisposal(assetId, disposal);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping
    public ResponseEntity<List<DisposalRecord>> getAllDisposals() {
        List<DisposalRecord> disposals = disposalRecordService.getAllDisposals();
        return ResponseEntity.ok(disposals);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DisposalRecord> getDisposal(@PathVariable Long id) {
        DisposalRecord disposal = disposalRecordService.getDisposal(id);
        return ResponseEntity.ok(disposal);
    }
}