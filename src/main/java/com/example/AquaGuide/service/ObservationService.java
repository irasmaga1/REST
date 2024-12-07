package com.example.AquaGuide.service;

import com.example.AquaGuide.dto.ObservationCreationDto;
import com.example.AquaGuide.dto.ObservationDto;
import com.example.AquaGuide.entity.Observation;
import com.example.AquaGuide.exception.ObservationAlreadyExists;
import com.example.AquaGuide.exception.ObservationNotFound;
import com.example.AquaGuide.mapper.ObservationMapper;
import com.example.AquaGuide.repository.ObservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ObservationService {
    public ObservationService(ObservationMapper observationMapper, ObservationRepository observationRepository) {
        this.observationMapper = observationMapper;
        this.observationRepository = observationRepository;
    }

    public final ObservationMapper observationMapper;
    public final ObservationRepository observationRepository;


    public ObservationDto getObservationById(Long id) {
        Observation observation = observationRepository.findById(id)
                .orElseThrow(() -> {
                    return new ObservationNotFound("Observation with ID: " + id + " not found.");
                });
        return observationMapper.toDto(observation);
    }

    public List<ObservationDto> getAllObservations() {
        List<Observation> observations = observationRepository.findAll();
        return observations.stream()
                .map(observationMapper::toDto)
                .toList();
    }

    @Transactional
    public ObservationDto createObservation(ObservationCreationDto observationCreationDto){
        if (observationRepository.existsByObserveDateAndObserverName(observationCreationDto.observeDate(), observationCreationDto.observerName())){
            throw new ObservationAlreadyExists("Observation with date: "+observationCreationDto.observeDate()+" and observer name: "+observationCreationDto.observerName()+" already exists.");
        }
        return  observationMapper.toDto(observationRepository.save(observationMapper.toEntity(observationCreationDto)));
    }

    @Transactional
    public ObservationDto updateObservation(Long id, ObservationDto updatedObservation){
        Observation observation = observationRepository.findById(id)
                .orElseThrow(()-> new ObservationNotFound("Observation with ID: " + id + " not found."));
        observation.setObserverName(updatedObservation.observerName());
        observation.setObserveDate(updatedObservation.observeDate());
        observation.setPhLevel(updatedObservation.phLevel());
        observation.setTemperature(updatedObservation.temperature());
        observation.setWaterQuality(updatedObservation.waterQuality());
        observation.setWildlifePresent(updatedObservation.wildlifePresent());

        Observation updatedObservationEntity = observationRepository.save(observation);
        return observationMapper.toDto(updatedObservationEntity);
    }

    @Transactional
    public void deleteObservation(Long id){
        Observation observation = observationRepository.findById(id)
                .orElseThrow(()-> new ObservationNotFound("Observation with ID: " + id + " not found."));
        observationRepository.deleteById(id);
    }
}
