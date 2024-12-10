package com.example.AquaGuide.mapper;

import com.example.AquaGuide.dto.WaterCreationDto;
import com.example.AquaGuide.dto.WaterDto;
import com.example.AquaGuide.entity.Water;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WaterMapper {
    Water toEntity(WaterDto waterDto);

    @AfterMapping
    default void linkObservations(@MappingTarget Water water) {
        if (water.getObservations() != null) {
            water.getObservations().forEach(observation -> observation.setWaterBody(water));
        }
    }

    @Mapping(target = "observations", ignore = true) // Уникаємо рекурсії
    @Mapping(target = "region", ignore = true) // Уникаємо рекурсії
    WaterDto toDto(Water water);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Water partialUpdate(WaterDto waterDto, @MappingTarget Water water);

    Water toEntity(WaterCreationDto waterCreationDto);
}
