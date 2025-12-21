package com.example.demo.controller;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.service.LifecycleEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lifecycle")
public class LifecycleEventController {

    private final LifecycleEventService lifecycleService;

    public LifecycleEventController(LifecycleEventService lifecycleService) {
        this.lifecycleService = lifecycleService;
    }

    // Create lifecycle event (audit trail)
    @PostMapping
    public LifecycleEvent createEvent(@RequestBody LifecycleEvent event) {
        return lifecycleService.createEvent(event);
    }

    // Get lifecycle events by asset
    @GetMapping("/asset/{assetId}")
    public List<LifecycleEvent> getEventsByAsset(@PathVariable Long assetId) {
        return lifecycleService.getEventsByAsset(assetId);
    }
}
