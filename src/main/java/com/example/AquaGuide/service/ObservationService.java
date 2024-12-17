package com.example.AquaGuide.service;

import com.example.AquaGuide.dto.ObservationDto;
import com.example.AquaGuide.entity.Observation;
import com.example.AquaGuide.exception.ObservationAlreadyExists;
import com.example.AquaGuide.exception.ObservationNotFound;
import com.example.AquaGuide.mapper.ObservationMapper;
import com.example.AquaGuide.repository.ObservationRepository;
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
public class ObservationService {
    private final ObservationMapper observationMapper;
    private final ObservationRepository observationRepository;

    public ObservationService(ObservationMapper observationMapper, ObservationRepository observationRepository) {
        this.observationMapper = observationMapper;
        this.observationRepository = observationRepository;
    }

    @Cacheable(value = "observations", key = "#id")
    public ObservationDto getObservationById(Long id) {
        Observation observation = observationRepository.findById(id)
                .orElseThrow(() -> {
                    return new ObservationNotFound("Observation with ID: " + id + " not found.");
                });
        return observationMapper.toDto(observation);
    }

    @Cacheable(value = "observations", key = "#page + '-' + #size + '-' + #sortBy + '-' + #direction")
    public Page<ObservationDto> getAllPaginatedObservations(int page, int size, String  sortBy, String  direction){
        Sort sort = direction.equalsIgnoreCase("desc")? Sort.by(Sort.Order.desc(sortBy)):Sort.by(Sort.Order.asc(sortBy));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Observation> observationPage = observationRepository.findAll(pageable);
        return observationPage.map(observationMapper::toDto);
    }

    @Transactional
    @Cacheable(value = "observations", key = "#result.id")
    public ObservationDto createObservation(ObservationCreationDto observationCreationDto){
        if (observationRepository.existsByObserveDateAndObserverName(observationCreationDto.observeDate(), observationCreationDto.observerName())){
            throw new ObservationAlreadyExists("Observation with date: "+observationCreationDto.observeDate()+" and observer name: "+observationCreationDto.observerName()+" already exists.");
        }
        return  observationMapper.toDto(observationRepository.save(observationMapper.toEntity(observationCreationDto)));
    }

    @Transactional
    @Cacheable(value = "observations",key = "#id")
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
    @CacheEvict(value = "observations", key = "#id")
    public void deleteObservation(Long id){
        Observation observation = observationRepository.findById(id)
                .orElseThrow(()-> new ObservationNotFound("Observation with ID: " + id + " not found."));
        observationRepository.deleteById(id);
    }
}
