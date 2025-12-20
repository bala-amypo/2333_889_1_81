package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class DisposalRecordController {

    private final DisposalRecordService disposalService;

    public DisposalRecordController(DisposalRecordService disposalService) {
        this.disposalService = disposalService;
    }

    // Create disposal record
    @PostMapping
    public DisposalRecord createDisposal(@RequestBody DisposalRecord record) {
        return disposalService.createDisposal(record);
    }
}
