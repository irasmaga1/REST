package com.example.AquaGuide.controller;

import com.example.AquaGuide.dto.RegionCreationDto;
import com.example.AquaGuide.dto.RegionDto;
import com.example.AquaGuide.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    private final RegionService regionService;

    @GetMapping("/{id}")
    public ResponseEntity<RegionDto> getRegionById(@PathVariable Long id){
        return ResponseEntity.ok(regionService.getRegionById(id));
    }

    @GetMapping
    public ResponseEntity<List<RegionDto>> getAllRegions (){
        return ResponseEntity.ok(regionService.getAllRegions());
    }

    @PostMapping
    public ResponseEntity<RegionDto> createRegion(@Valid @RequestBody RegionCreationDto regionCreationDto){
        return new ResponseEntity(regionService.createRegion(regionCreationDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDto> updateRegion(@PathVariable Long id, @RequestBody RegionDto updatedRegion){
        RegionDto updated = regionService.updateRegion(id, updatedRegion);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable Long id){
        regionService.deleteRegion(id);
        return ResponseEntity.ok("Region deleted successfully.");
    }
}