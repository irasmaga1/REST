package com.example.AquaGuide.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.AquaGuide.entity.Region}
 */
public record RegionCreationDto(String name, int population, String description) implements Serializable {
}