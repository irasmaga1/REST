package com.example.AquaGuide.service;

import com.example.AquaGuide.dto.RegionCreationDto;
import com.example.AquaGuide.dto.RegionDto;
import com.example.AquaGuide.entity.Region;
import com.example.AquaGuide.exception.RegionAlreadyExists;
import com.example.AquaGuide.exception.RegionNotFound;
import com.example.AquaGuide.mapper.RegionMapper;
import com.example.AquaGuide.repository.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RegionService {
    public RegionService(RegionMapper regionMapper, RegionRepository regionRepository) {
        this.regionMapper = regionMapper;
        this.regionRepository = regionRepository;
    }

    private final RegionMapper regionMapper;
    private final RegionRepository regionRepository;

    public RegionDto getRegionById(Long id){
        Region region = regionRepository.findById(id)
                .orElseThrow(()->{
                    return new RegionNotFound("Region with ID: "+id+" not found");
                });
        return regionMapper.toDto(region);
    }

    public List<RegionDto> getAllRegions(){
        List<Region> regions=regionRepository.findAll();
        return regions.stream()
                .map(regionMapper::toDto)
                .toList();
    }

    @Transactional
    public RegionDto createRegion (RegionCreationDto regionCreationDto){
        if (regionRepository.existsByName(regionCreationDto.name())){
            throw new RegionAlreadyExists("Region with name: "+regionCreationDto.name()+" already exists.");
        }
        return regionMapper.toDto(regionRepository.save(regionMapper.toEntity(regionCreationDto)));
    }

    @Transactional
    public RegionDto updateRegion(Long id, RegionDto updatedRegion){
        Region region = regionRepository.findById(id)
                .orElseThrow(()-> new RegionNotFound("Region with id: "+id+" not found."));
        region.setName(updatedRegion.name());
        region.setPopulation(updatedRegion.population());
        region.setDescription(updatedRegion.description());

        Region updatedRegionEntity = regionRepository.save(region);
        return regionMapper.toDto(updatedRegionEntity);
    }

    @Transactional
    public void deleteRegion(Long id){
        Region region = regionRepository.findById(id)
                .orElseThrow(()-> new RegionNotFound("Region with ID: "+id+" not found."));
        regionRepository.deleteById(id);
    }
}
