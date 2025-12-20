package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Disposals", description = "Asset disposal record endpoints")
public class DisposalRecordController {

    private final DisposalRecordService disposalRecordService;

    public DisposalRecordController(DisposalRecordService disposalRecordService) {
        this.disposalRecordService = disposalRecordService;
    }

    @PostMapping("/{assetId}")
    @Operation(summary = "Create disposal", description = "Creates a disposal record and marks asset as DISPOSED (requires ADMIN)")
    public ResponseEntity<DisposalRecord> createDisposal(@PathVariable Long assetId,
                                                        @RequestBody DisposalRecord disposal) {
        DisposalRecord created = disposalRecordService.createDisposal(assetId, disposal);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    @Operation(summary = "Get all disposals", description = "Retrieves all disposal records")
    public ResponseEntity<List<DisposalRecord>> getAllDisposals() {
        List<DisposalRecord> disposals = disposalRecordService.getAllDisposals();
        return ResponseEntity.ok(disposals);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get disposal by ID", description = "Retrieves a specific disposal record by ID")
    public ResponseEntity<DisposalRecord> getDisposal(@PathVariable Long id) {
        DisposalRecord disposal = disposalRecordService.getDisposal(id);
        return ResponseEntity.ok(disposal);
    }
}
