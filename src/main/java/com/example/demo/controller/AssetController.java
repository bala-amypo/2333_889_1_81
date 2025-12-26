// package com.example.demo.controller;

// import com.example.demo.entity.Asset;
// import com.example.demo.service.AssetService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/assets")
// public class AssetController {

//     @Autowired
//     private AssetService assetService;

//     @PostMapping
//     public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
//         return ResponseEntity.ok(assetService.createAsset(asset));
//     }

//     @GetMapping
//     public List<Asset> getAllAssets() {
//         return assetService.getAllAssets();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Asset> getAsset(@PathVariable Long id) {
//         return ResponseEntity.ok(assetService.getAsset(id));
//     }

//     @PostMapping("/status/{id}")
//     public ResponseEntity<Asset> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
//         return ResponseEntity.ok(assetService.updateStatus(id, request.get("status")));
//     }
// }
package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    public Asset create(@RequestBody Asset asset) {
        return assetService.createAsset(asset);
    }

    @GetMapping
    public List<Asset> getAll() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public Asset getById(@PathVariable Long id) {
        return assetService.getAsset(id);
    }

    @PutMapping("/status/{id}")
    public Asset updateStatus(@PathVariable Long id,
                              @RequestParam String status) {
        return assetService.updateStatus(id, status);
    }
}
