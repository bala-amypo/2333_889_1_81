package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lifecycle_events")
public class LifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;
    private String eventDescription;
    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    @JsonBackReference
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "performed_by")
    private User performedBy;

    @PrePersist
    public void prePersist() {
        if (eventDate == null) {
            eventDate = LocalDateTime.now();
        }
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public String getEventType() { return eventType; }
    public String getEventDescription() { return eventDescription; }
    public LocalDateTime getEventDate() { return eventDate; }
    public Asset getAsset() { return asset; }
    public User getPerformedBy() { return performedBy; }

    public void setId(Long id) { this.id = id; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }
    public void setAsset(Asset asset) { this.asset = asset; }
    public void setPerformedBy(User performedBy) { this.performedBy = performedBy; }
}
