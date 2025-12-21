package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "disposal_records")
public class DisposalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonBackReference
    private Asset asset;

    private String disposalMethod;
    private LocalDate disposalDate;

    @ManyToOne
    @JoinColumn(name = "approved_by", nullable = false)
    @JsonBackReference
    private User approvedBy;

    private String notes;
    private LocalDateTime createdAt;

    public DisposalRecord() {
    }

    public DisposalRecord(Long id, Asset asset, String disposalMethod,
                          LocalDate disposalDate, User approvedBy,
                          String notes, LocalDateTime createdAt) {
        this.id = id;
        this.asset = asset;
        this.disposalMethod = disposalMethod;
        this.disposalDate = disposalDate;
        this.approvedBy = approvedBy;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public LocalDate getDisposalDate() {
        return disposalDate;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }
}
