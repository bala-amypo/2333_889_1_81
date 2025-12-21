package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
public class DisposalRecordController {

    private final DisposalRecordService disposalService;

    public DisposalRecordController(DisposalRecordService disposalService) {
        this.disposalService = disposalService;
    }

    // ✅ CREATE disposal record
    @PostMapping
    public DisposalRecord createDisposal(@RequestBody DisposalRecord record) {
        return disposalService.createDisposal(record);
    }

    // ✅ GET all disposal records
    @GetMapping
    public List<DisposalRecord> getAllDisposals() {
        return disposalService.getAllDisposals();
    }

    // ✅ GET disposal records by assetId
    @GetMapping("/asset/{assetId}")
    public List<DisposalRecord> getDisposalsByAsset(@PathVariable Long assetId) {
        return disposalService.getDisposalsByAsset(assetId);
    }
}
