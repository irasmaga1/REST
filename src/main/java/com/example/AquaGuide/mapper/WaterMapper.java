package com.example.AquaGuide.mapper;

import com.example.AquaGuide.dto.WaterCreationDto;
import com.example.AquaGuide.dto.WaterDto;
import com.example.AquaGuide.entity.Water;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ObservationMapper.class, RegionMapper.class})
public interface WaterMapper {
    Water toEntity(WaterDto waterDto);

    @AfterMapping
    default void linkObservations(@MappingTarget Water water) {
        water.getObservations().forEach(observation -> observation.setWaterBody(water));
    }

    WaterDto toDto(Water water);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Water partialUpdate(WaterDto waterDto, @MappingTarget Water water);

    Water toEntity(WaterCreationDto waterCreationDto);

    WaterCreationDto toDto1(Water water);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Water partialUpdate(WaterCreationDto waterCreationDto, @MappingTarget Water water);
}