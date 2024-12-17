package com.example.AquaGuide.mapper;

import com.example.AquaGuide.dto.RegionCreationDto;
import com.example.AquaGuide.dto.RegionDto;
import com.example.AquaGuide.entity.Region;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ObservationMapper.class, WaterMapper.class})
public interface RegionMapper {
    Region toEntity(RegionDto regionDto);

    @AfterMapping
    default void linkObservations(@MappingTarget Region region) {
        region.getObservations().forEach(observation -> observation.setRegion(region));
    }

    @AfterMapping
    default void linkWaterBodies(@MappingTarget Region region) {
        region.getWaterBodies().forEach(waterBody -> waterBody.setRegion(region));
    }

    RegionDto toDto(Region region);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Region partialUpdate(RegionDto regionDto, @MappingTarget Region region);

    Region toEntity(RegionCreationDto regionCreationDto);

    RegionCreationDto toDto1(Region region);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Region partialUpdate(RegionCreationDto regionCreationDto, @MappingTarget Region region);
}