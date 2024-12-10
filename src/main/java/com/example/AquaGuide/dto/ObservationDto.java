package com.example.AquaGuide.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.AquaGuide.entity.Observation}
 */
public record ObservationDto(Long id, String observerName, String observeDate, double phLevel, double temperature, String waterQuality, String wildlifePresent, RegionDto region, WaterDto waterBody) implements Serializable {
  }