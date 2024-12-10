package com.example.AquaGuide.mapper;

import com.example.AquaGuide.dto.ObservationCreationDto;
import com.example.AquaGuide.dto.ObservationDto;
import com.example.AquaGuide.entity.Observation;
import com.example.AquaGuide.entity.Region;
import com.example.AquaGuide.entity.Water;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ObservationMapper {

    @Mapping(target = "region", ignore = true)  // Ігноруємо це поле для уникнення рекурсії
    @Mapping(target = "waterBody", ignore = true)  // Ігноруємо це поле для уникнення рекурсії
    Observation toEntity(ObservationDto observationDto);

    @AfterMapping
    default void setRegionAndWaterBody(@MappingTarget Observation observation, ObservationDto observationDto) {
        if (observationDto.region() != null) {
            // Перевіряємо, чи передано id для region
            Region region = new Region();
            region.setId(observationDto.region().id());
            observation.setRegion(region);
        }

        if (observationDto.waterBody() != null) {
            // Перевіряємо, чи передано id для waterBody
            Water waterBody = new Water();
            waterBody.setId(observationDto.waterBody().id());
            observation.setWaterBody(waterBody);
        }
    }

    @Mapping(target = "region", ignore = true)  // Ігноруємо це поле для уникнення рекурсії
    @Mapping(target = "waterBody", ignore = true)  // Ігноруємо це поле для уникнення рекурсії
    ObservationDto toDto(Observation observation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Observation partialUpdate(ObservationDto observationDto, @MappingTarget Observation observation);

    Observation toEntity(ObservationCreationDto observationCreationDto);
}

