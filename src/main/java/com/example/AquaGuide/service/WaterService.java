package com.example.AquaGuide.service;

import com.example.AquaGuide.mapper.WaterMapper;
import com.example.AquaGuide.repository.WaterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WaterService {
    public WaterService(WaterMapper waterMapper, WaterRepository waterRepository) {
        this.waterMapper = waterMapper;
        this.waterRepository = waterRepository;
    }

    private final WaterMapper waterMapper;
    private final WaterRepository waterRepository;
}
