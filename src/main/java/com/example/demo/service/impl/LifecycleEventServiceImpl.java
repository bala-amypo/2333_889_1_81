package com.example.demo.service.impl;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.LifecycleEventRepository;
import com.example.demo.service.LifecycleEventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LifecycleEventServiceImpl implements LifecycleEventService {

    private final LifecycleEventRepository lifecycleRepo;
    private final AssetRepository assetRepo;

    public LifecycleEventServiceImpl(LifecycleEventRepository lifecycleRepo,
                                     AssetRepository assetRepo) {
        this.lifecycleRepo = lifecycleRepo;
        this.assetRepo = assetRepo;
    }

    @Override
    public LifecycleEvent createEvent(LifecycleEvent event) {

        assetRepo.findById(event.getAsset().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        event.setEventTime(LocalDate.now()); // ✅ CORRECT
        return lifecycleRepo.save(event);
    }

    @Override
    public List<LifecycleEvent> getEventsByAsset(Long assetId) {
        return lifecycleRepo.findByAssetId(assetId);
    }
}
