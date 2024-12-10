package com.example.AquaGuide.repository;

import com.example.AquaGuide.entity.Observation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    boolean existsByObserveDateAndObserverName(String observeDate, String observerName);

    @Override
    Page<Observation> findAll(Pageable pageable);
    List<Observation> findAll(Sort sort);
}
