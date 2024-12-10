package com.example.AquaGuide.repository;

import com.example.AquaGuide.entity.Water;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaterRepository extends JpaRepository<Water, Long> {
    boolean existsByName(String name);
    @Override
    Page<Water> findAll(Pageable pageable);
    List<Water> findAll (Sort sort);

}
