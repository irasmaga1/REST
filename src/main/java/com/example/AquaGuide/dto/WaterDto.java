package com.example.AquaGuide.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.AquaGuide.entity.Water}
 */
public record WaterDto(Long id, String name, String type, double area, double depth, String location,
                       String description) implements Serializable {
}