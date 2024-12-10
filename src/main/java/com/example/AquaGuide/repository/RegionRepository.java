package com.example.AquaGuide.repository;

import com.example.AquaGuide.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByName(String name);

    @Override
    Page<Region> findAll (Pageable pageable);
    List<Region> findAll(Sort sort);
}
