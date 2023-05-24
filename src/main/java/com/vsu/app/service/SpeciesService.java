package com.vsu.app.service;

import com.vsu.app.entity.Species;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.SpeciesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpeciesService {
    private SpeciesRepository speciesRepository;
    private ProfileService profileService;

    //TODO: optimistic pesimistic lock
    public boolean add(Long adminId, String name) throws UnauthorizedAccessException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only administrator can add species");
        }
        return  speciesRepository.create(name);
    }

    public boolean delete(Long adminId, Long speciesId) throws UnauthorizedAccessException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only administrator can delete species");
        }

        return speciesRepository.delete(speciesId);
    }

    public List<String> getAll() {
        return speciesRepository.getAll().stream().map(Species::getName).collect(Collectors.toList());
    }

    public Species getById(Long id){
        return speciesRepository.get(id);
    }
}
