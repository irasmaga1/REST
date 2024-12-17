package com.example.AquaGuide.service;

import com.example.AquaGuide.dto.RegionCreationDto;
import com.example.AquaGuide.dto.RegionDto;
import com.example.AquaGuide.entity.Region;
import com.example.AquaGuide.exception.RegionAlreadyExists;
import com.example.AquaGuide.exception.RegionNotFound;
import com.example.AquaGuide.mapper.RegionMapper;
import com.example.AquaGuide.repository.RegionRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RegionService {
    private final RegionMapper regionMapper;
    private final RegionRepository regionRepository;

    public RegionService(RegionMapper regionMapper, RegionRepository regionRepository) {
        this.regionMapper = regionMapper;
        this.regionRepository = regionRepository;
    }

    @Cacheable(value = "regions", key = "#id")
    public RegionDto getRegionById(Long id){
        Region region = regionRepository.findById(id)
                .orElseThrow(()->{
                    return new RegionNotFound("Region with ID: "+id+" not found");
                });
        return regionMapper.toDto(region);
    }

    @Cacheable(value = "regions", key = "#page + '-' + #size + '-' + #sortBy + '-' + #direction")
    public Page<RegionDto> getAllPaginatedRegions(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("desc")?Sort.by(Sort.Order.desc(sortBy)):Sort.by(Sort.Order.asc(sortBy));
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Region> regionPage = regionRepository.findAll(pageable);
        return  regionPage.map(regionMapper::toDto);
    }


    @Transactional
    @Cacheable(value = "regions", key = "#result.id")
    public RegionDto createRegion (RegionCreationDto regionCreationDto){
        if (regionRepository.existsByName(regionCreationDto.name())){
            throw new RegionAlreadyExists("Region with name: "+regionCreationDto.name()+" already exists.");
        }
        return regionMapper.toDto(regionRepository.save(regionMapper.toEntity(regionCreationDto)));
    }

    @Transactional
    @Cacheable(value = "regions", key = "#id")
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
    @CacheEvict(value = "regions", key = "#id")
    public void deleteRegion(Long id){
        Region region = regionRepository.findById(id)
                .orElseThrow(()-> new RegionNotFound("Region with ID: "+id+" not found."));
        regionRepository.deleteById(id);
    }
}
