package com.example.AquaGuide.mapper;

import com.example.AquaGuide.dto.WaterCreationDto;
import com.example.AquaGuide.dto.WaterDto;
import com.example.AquaGuide.entity.Water;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WaterMapper {
    Water toEntity(WaterDto waterDto);

    WaterDto toDto(Water water);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Water partialUpdate(WaterDto waterDto, @MappingTarget Water water);

    Water toEntity(WaterCreationDto waterCreationDto);
}