// package com.example.demo.controller;

// import com.example.demo.entity.DisposalRecord;
// import com.example.demo.service.DisposalRecordService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/disposals")
// public class DisposalRecordController {

//     @Autowired
//     private DisposalRecordService disposalRecordService;

//     @PostMapping
//     public ResponseEntity<DisposalRecord> createDisposal(@RequestBody DisposalRecord record) {
//         Long assetId = record.getAsset() != null ? record.getAsset().getId() : null;
//         if (assetId == null) {
//             return ResponseEntity.badRequest().build();
//         }
//         DisposalRecord savedRecord = disposalRecordService.createDisposal(assetId, record);
//         return ResponseEntity.ok(savedRecord);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<DisposalRecord> getDisposalById(@PathVariable Long id) {
//         try {
//             DisposalRecord record = disposalRecordService.getDisposal(id);
//             return ResponseEntity.ok(record);
//         } catch (RuntimeException e) {
//             return ResponseEntity.notFound().build();
//         }
//     }
// }
package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
public class DisposalRecordController {

    private final DisposalRecordService disposalRecordService;

    public DisposalRecordController(DisposalRecordService disposalRecordService) {
        this.disposalRecordService = disposalRecordService;
    }

    @PostMapping("/{assetId}")
    public DisposalRecord create(@PathVariable Long assetId,
                                 @RequestBody DisposalRecord record) {
        return disposalRecordService.createDisposal(assetId, record);
    }

    @GetMapping
    public List<DisposalRecord> getAll() {
        return disposalRecordService.getAllDisposals();
    }

    @GetMapping("/{id}")
    public DisposalRecord getById(@PathVariable Long id) {
        return disposalRecordService.getDisposal(id);
    }
}
