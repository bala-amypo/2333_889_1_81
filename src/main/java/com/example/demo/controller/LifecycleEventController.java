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

    @GetMapping("/asset/{assetId}")
    public List<LifecycleEvent> getEventsForAsset(@PathVariable Long assetId) {
        return lifecycleEventService.getEventsForAsset(assetId);
    }

    @GetMapping("/{id}")
    public LifecycleEvent getEvent(@PathVariable Long id) {
        return lifecycleEventService.getEvent(id);
    }
}
