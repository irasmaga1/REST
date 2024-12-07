package com.example.AquaGuide.service;

import com.example.AquaGuide.dto.WaterCreationDto;
import com.example.AquaGuide.dto.WaterDto;
import com.example.AquaGuide.entity.Water;
import com.example.AquaGuide.exception.WaterAlreadyExists;
import com.example.AquaGuide.exception.WaterNotFound;
import com.example.AquaGuide.mapper.WaterMapper;
import com.example.AquaGuide.repository.WaterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WaterService {
    public WaterService(WaterMapper waterMapper, WaterRepository waterRepository) {
        this.waterMapper = waterMapper;
        this.waterRepository = waterRepository;
    }

    private final WaterMapper waterMapper;
    private final WaterRepository waterRepository;

    public WaterDto getWaterById(Long id) {
        Water water = waterRepository.findById(id)
                .orElseThrow(() -> {
                    return new WaterNotFound("Water body with ID: " + id + " not found.");
                });
        return waterMapper.toDto(water);
    }

    public List<WaterDto> getAllWaters() {
        List<Water> waters = waterRepository.findAll();
        return waters.stream()
                .map(waterMapper::toDto)
                .toList();
    }

    @Transactional
    public WaterDto createWater(WaterCreationDto waterCreationDto) {
        if (waterRepository.existsByName(waterCreationDto.name())) {
            throw new WaterAlreadyExists("Water body with name: " + waterCreationDto.name() + " already exists.");
        }
        return waterMapper.toDto(waterRepository.save(waterMapper.toEntity(waterCreationDto)));
    }

    @Transactional
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
    public void deleteWater(Long id) {
        Water water = waterRepository.findById(id)
                .orElseThrow(() -> new WaterNotFound("Water body with ID: " + id + " not found."));
        waterRepository.deleteById(id);
    }
}
