// package com.example.demo.controller;

// import com.example.demo.entity.TransferRecord;
// import com.example.demo.service.TransferRecordService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/transfers")
// public class TransferRecordController {

//     @Autowired
//     private TransferRecordService transferRecordService;

//     @PostMapping
//     public ResponseEntity<?> createTransfer(@RequestBody TransferRecord record) {
//         try {
//             Long assetId = (record.getAsset() != null) ? record.getAsset().getId() : null;
//             if (assetId == null) {
//                 return ResponseEntity.badRequest().body("Asset ID is required");
//             }
//             TransferRecord savedRecord = transferRecordService.createTransfer(assetId, record);
//             return ResponseEntity.ok(savedRecord);
//         } catch (RuntimeException e) {
//             return ResponseEntity.badRequest().body(e.getMessage());
//         }
//     }

//     @GetMapping("/asset/{assetId}")
//     public List<TransferRecord> getTransfersForAsset(@PathVariable Long assetId) {
//         return transferRecordService.getTransfersForAsset(assetId);
//     }
// }
package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferRecordController {

    private final TransferRecordService transferRecordService;

    public TransferRecordController(TransferRecordService transferRecordService) {
        this.transferRecordService = transferRecordService;
    }

    @PostMapping("/{assetId}")
    public TransferRecord create(@PathVariable Long assetId,
                                 @RequestBody TransferRecord record) {
        return transferRecordService.createTransfer(assetId, record);
    }

    @GetMapping("/asset/{assetId}")
    public List<TransferRecord> getByAsset(@PathVariable Long assetId) {
        return transferRecordService.getTransfersForAsset(assetId);
    }

    @GetMapping("/{id}")
    public TransferRecord getById(@PathVariable Long id) {
        return transferRecordService.getTransfer(id);
    }
}
