package com.example.demo.controller;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.service.LifecycleEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Lifecycle Events", description = "Asset lifecycle event tracking endpoints")
public class LifecycleEventController {
    
    private final LifecycleEventService lifecycleEventService;
    
    public LifecycleEventController(LifecycleEventService lifecycleEventService) {
        this.lifecycleEventService = lifecycleEventService;
    }
    
    @PostMapping("/{assetId}/{userId}")
    @Operation(summary = "Log event", description = "Logs a lifecycle event for an asset")
    public ResponseEntity<LifecycleEvent> logEvent(@PathVariable Long assetId,
                                                   @PathVariable Long userId,
                                                   @RequestBody LifecycleEvent event) {
        LifecycleEvent logged = lifecycleEventService.logEvent(assetId, userId, event);
        return ResponseEntity.ok(logged);
    }
    
    @GetMapping("/asset/{assetId}")
    @Operation(summary = "Get events for asset", description = "Retrieves all lifecycle events for a specific asset")
    public ResponseEntity<List<LifecycleEvent>> getEventsForAsset(@PathVariable Long assetId) {
        List<LifecycleEvent> events = lifecycleEventService.getEventsForAsset(assetId);
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get event by ID", description = "Retrieves a specific lifecycle event by ID")
    public ResponseEntity<LifecycleEvent> getEvent(@PathVariable Long id) {
        LifecycleEvent event = lifecycleEventService.getEvent(id);
        return ResponseEntity.ok(event);
    }
}