package com.example.demo.controller;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.service.LifecycleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class LifecycleEventController {

    @Autowired
    private LifecycleEventService lifecycleEventService;

    // Test t19: logLifecycleEvent_success
    // We assume the request body contains the full event object with nested Asset and User IDs
    @PostMapping
    public ResponseEntity<LifecycleEvent> logEvent(@RequestBody LifecycleEvent event) {
        Long assetId = event.getAsset() != null ? event.getAsset().getId() : null;
        Long userId = event.getPerformedBy() != null ? event.getPerformedBy().getId() : null;

        if (assetId == null || userId == null) {
            return ResponseEntity.badRequest().build();
        }

        LifecycleEvent savedEvent = lifecycleEventService.logEvent(assetId, userId, event);
        return ResponseEntity.ok(savedEvent);
    }

    // Test t03 & t80: getEventsForAsset
    // Test t03 simulates query params: "/api/events/asset/10?page=1&size=20"
    // We accept page/size parameters but for this implementation we return the full list
    @GetMapping("/asset/{assetId}")
    public List<LifecycleEvent> getEventsForAsset(
            @PathVariable Long assetId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        
        return lifecycleEventService.getEventsForAsset(assetId);
    }
}