package com.example.AquaGuide.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.AquaGuide.entity.Region}
 */
public record RegionCreationDto(String name, int population, String description, List<ObservationDto> observations,
                                List<WaterDto> waterBodies) implements Serializable {
}