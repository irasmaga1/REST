package com.example.AquaGuide.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.AquaGuide.entity.Observation}
 */
public record ObservationCreationDto(String observerName, String observeDate, double phLevel, double temperature,
                                     String waterQuality, String wildlifePresent) implements Serializable {
}