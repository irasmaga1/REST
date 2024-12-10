package com.example.AquaGuide.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.AquaGuide.entity.Water}
 */
public record WaterCreationDto(String name, String type, double area, double depth, String location,
                               String description, List<ObservationDto> observations,
                               RegionDto region) implements Serializable {
}