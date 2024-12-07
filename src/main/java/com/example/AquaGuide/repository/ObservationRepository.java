package com.example.AquaGuide.repository;

import com.example.AquaGuide.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    boolean existsByObserveDateAndObserverName(String observeDate, String observerName);
}
