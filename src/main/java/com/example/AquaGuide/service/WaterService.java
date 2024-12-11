package com.example.AquaGuide.service;

import com.example.AquaGuide.dto.WaterCreationDto;
import com.example.AquaGuide.dto.WaterDto;
import com.example.AquaGuide.entity.Water;
import com.example.AquaGuide.exception.WaterAlreadyExists;
import com.example.AquaGuide.exception.WaterNotFound;
import com.example.AquaGuide.mapper.WaterMapper;
import com.example.AquaGuide.repository.WaterRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WaterService {
    private final WaterMapper waterMapper;
    private final WaterRepository waterRepository;

    public WaterService(WaterMapper waterMapper, WaterRepository waterRepository) {
        this.waterMapper = waterMapper;
        this.waterRepository = waterRepository;
    }

    @Cacheable(value = "waters", key = "#id")
    public WaterDto getWaterById(Long id) {
        Water water = waterRepository.findById(id)
                .orElseThrow(() -> {
                    return new WaterNotFound("Water body with ID: " + id + " not found.");
                });
        return waterMapper.toDto(water);
    }

    @Cacheable(value = "waters", key = "#page + '-' + #size + '-' + #sortBy + '-' + #direction")
    public Page<WaterDto> getAllPaginatedWaters(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("desc")?Sort.by(Sort.Order.desc(sortBy)):Sort.by(Sort.Order.asc(sortBy));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Water> waterPage = waterRepository.findAll(pageable);
        return waterPage.map(waterMapper::toDto);
    }
    @Transactional
    @Cacheable(value = "waters", key = "#result.id")
    public WaterDto createWater(WaterCreationDto waterCreationDto) {
        if (waterRepository.existsByName(waterCreationDto.name())) {
            throw new WaterAlreadyExists("Water body with name: " + waterCreationDto.name() + " already exists.");
        }
        return waterMapper.toDto(waterRepository.save(waterMapper.toEntity(waterCreationDto)));
    }

    @Transactional
    @Cacheable(value = "waters", key = "#id")
    public WaterDto updateWater(Long id, WaterDto updatedWater) {
        Water water = waterRepository.findById(id)
                .orElseThrow(() -> new WaterNotFound("Water body with ID: " + id + " not found."));
        water.setName(updatedWater.name());
        water.setType(updatedWater.type());
        water.setArea(updatedWater.area());
        water.setDepth(updatedWater.depth());
        water.setLocation(updatedWater.location());
        water.setDescription(updatedWater.description());

        Water updatedWaterEntity = waterRepository.save(water);
        return waterMapper.toDto(updatedWaterEntity);
    }

    @Transactional
    @CacheEvict(value = "waters", key = "#id")
    public void deleteWater(Long id) {
        Water water = waterRepository.findById(id)
                .orElseThrow(() -> new WaterNotFound("Water body with ID: " + id + " not found."));
        waterRepository.deleteById(id);
    }
}
