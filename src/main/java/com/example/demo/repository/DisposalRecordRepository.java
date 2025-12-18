package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DisposalRecord;

public interface DisposalRecordRepository extends JpaRepository<DisposalRecord, Long> {
}
