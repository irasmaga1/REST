package com.example.AquaGuide.repository;

import com.example.AquaGuide.entity.Water;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterRepository extends JpaRepository<Water, Long> {
    boolean existsByName(String name);
}
