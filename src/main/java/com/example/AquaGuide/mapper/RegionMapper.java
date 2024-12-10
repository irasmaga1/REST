package com.example.AquaGuide.mapper;

import com.example.AquaGuide.dto.RegionCreationDto;
import com.example.AquaGuide.dto.RegionDto;
import com.example.AquaGuide.entity.Region;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegionMapper {
    Region toEntity(RegionDto regionDto);

    @AfterMapping
    default void linkObservations(@MappingTarget Region region) {
        if (region.getObservations() != null) {
            region.getObservations().forEach(observation -> observation.setRegion(region));
        }
    }

    @AfterMapping
    default void linkWaterBodies(@MappingTarget Region region) {
        if (region.getWaterBodies() != null) {
            region.getWaterBodies().forEach(waterBody -> waterBody.setRegion(region));
        }
    }

    @Mapping(target = "observations", ignore = true) // Уникаємо рекурсії
    @Mapping(target = "waterBodies", ignore = true) // Уникаємо рекурсії
    RegionDto toDto(Region region);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Region partialUpdate(RegionDto regionDto, @MappingTarget Region region);

    Region toEntity(RegionCreationDto regionCreationDto);
}
