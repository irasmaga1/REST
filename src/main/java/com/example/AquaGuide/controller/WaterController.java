package com.example.AquaGuide.controller;

import com.example.AquaGuide.dto.WaterCreationDto;
import com.example.AquaGuide.dto.WaterDto;
import com.example.AquaGuide.service.WaterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waters")
public class WaterController {
    private final WaterService waterService;

    public WaterController(WaterService waterService) {
        this.waterService = waterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterDto> getWaterById(@PathVariable Long id){
        return ResponseEntity.ok(waterService.getWaterById(id));
    }

    @GetMapping
    public ResponseEntity<List<WaterDto>> getAllWaters(){
        return ResponseEntity.ok(waterService.getAllWaters());
    }

    @PostMapping
    public ResponseEntity<WaterDto> createWater(@Valid @RequestBody WaterCreationDto waterCreationDto){
        return new ResponseEntity(waterService.createWater(waterCreationDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterDto> updateWater(@PathVariable Long id, @RequestBody WaterDto updatedWater){
        WaterDto updated = waterService.updateWater(id, updatedWater);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWater(@PathVariable Long id){
        return ResponseEntity.ok("Water body deleted successfully.");
    }
}
