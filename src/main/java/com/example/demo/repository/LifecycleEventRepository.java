package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.LifecycleEvent;

public interface LifecycleEventRepository extends JpaRepository<LifecycleEvent, Long> {
}
