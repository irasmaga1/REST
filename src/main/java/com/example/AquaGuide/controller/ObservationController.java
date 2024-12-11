package com.example.AquaGuide.controller;

import com.example.AquaGuide.dto.ObservationCreationDto;
import com.example.AquaGuide.dto.ObservationDto;
import com.example.AquaGuide.service.ObservationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {
    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }

    private final ObservationService observationService;

    @GetMapping("/{id}")
    public ResponseEntity<ObservationDto> getObservationById(@PathVariable Long id){
        return ResponseEntity.ok(observationService.getObservationById(id));
    }

    @GetMapping
    public Page<ObservationDto> getAllObservations(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(defaultValue = "id")String sortBy,
            @RequestParam(defaultValue = "asc")String direction)
    {
        return observationService.getAllPaginatedObservations(page, size, sortBy, direction);
    }

    @PostMapping
    public ResponseEntity<ObservationDto>createObservation(@Valid @RequestBody ObservationCreationDto observationCreationDto){
        return new ResponseEntity(observationService.createObservation(observationCreationDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObservationDto> updateObservation(@PathVariable Long id, @RequestBody ObservationDto updatedObservation){
        ObservationDto updated = observationService.updateObservation(id, updatedObservation);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteObservation(@PathVariable Long id){
        observationService.deleteObservation(id);
        return ResponseEntity.ok("Observation deleted successfully.");
    }
}
