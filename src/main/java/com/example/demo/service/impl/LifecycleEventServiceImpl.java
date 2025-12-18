@Override
public LifecycleEvent createEvent(LifecycleEvent event) {

    assetRepo.findById(event.getAsset().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

    // ✅ MATCHES ENTITY FIELD
    event.setEventDate(LocalDate.now());

    return lifecycleRepo.save(event);
}

@Override
public List<LifecycleEvent> getEventsByAsset(Long assetId) {
    return lifecycleRepo.findByAsset_Id(assetId);
}
