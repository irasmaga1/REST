package com.example.AquaGuide.mapper;

import com.example.AquaGuide.dto.ObservationCreationDto;
import com.example.AquaGuide.dto.ObservationDto;
import com.example.AquaGuide.entity.Observation;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ObservationMapper {
    Observation toEntity(ObservationDto observationDto);

    @Mapping(target = "region", ignore = true) // Уникаємо рекурсії
    @Mapping(target = "waterBody", ignore = true) // Уникаємо рекурсії
    ObservationDto toDto(Observation observation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Observation partialUpdate(ObservationDto observationDto, @MappingTarget Observation observation);

    Observation toEntity(ObservationCreationDto observationCreationDto);
}
