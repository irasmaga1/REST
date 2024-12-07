package com.example.AquaGuide.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.AquaGuide.entity.Region}
 */
public record RegionDto(Long id, String name, int population, String description) implements Serializable {
}