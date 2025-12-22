package com.example.demo.controller;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.service.LifecycleEventService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class LifecycleEventController {

    private final LifecycleEventService lifecycleEventService;

    public LifecycleEventController(LifecycleEventService lifecycleEventService) {
        this.lifecycleEventService = lifecycleEventService;
    }

    // CREATE
    @PostMapping(
        value = "/{assetId}/{userId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LifecycleEvent> logEvent(
            @PathVariable Long assetId,
            @PathVariable Long userId,
            @RequestBody LifecycleEvent event) {

        return ResponseEntity.ok(
                lifecycleEventService.logEvent(assetId, userId, event)
        );
    }

    // READ ALL EVENTS FOR ASSET
    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<LifecycleEvent>> getEventsForAsset(
            @PathVariable Long assetId) {

        return ResponseEntity.ok(
                lifecycleEventService.getEventsForAsset(assetId)
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<LifecycleEvent> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(
                lifecycleEventService.getEvent(id)
        );
    }
}
