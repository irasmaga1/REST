package com.example.AquaGuide.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.AquaGuide.entity.Region}
 */
public record RegionDto(Long id, String name, int population, String description, List<ObservationDto> observations,
                        List<WaterDto> waterBodies) implements Serializable {
}