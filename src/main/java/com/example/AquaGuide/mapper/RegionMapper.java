package com.example.AquaGuide.mapper;

import com.example.AquaGuide.dto.RegionCreationDto;
import com.example.AquaGuide.dto.RegionDto;
import com.example.AquaGuide.entity.Region;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegionMapper {
    Region toEntity(RegionDto regionDto);

    RegionDto toDto(Region region);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Region partialUpdate(RegionDto regionDto, @MappingTarget Region region);

    Region toEntity(RegionCreationDto regionCreationDto);
}