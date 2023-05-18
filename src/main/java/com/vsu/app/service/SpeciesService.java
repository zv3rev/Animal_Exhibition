package com.vsu.app.service;

import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.SpeciesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpeciesService {
    private SpeciesRepository speciesRepository;
    private ProfileService profileService;

    public boolean add(Long adminId, String name) throws UnauthorizedAccessException {
        if (!profileService.checkAdminRole(adminId)){
            throw new UnauthorizedAccessException("Only administrator can add species");
        }
        return  speciesRepository.create(name);
    }

    public boolean delete(Long adminId, Long speciesId) throws UnauthorizedAccessException {
        if (!profileService.checkAdminRole(adminId)){
            throw new UnauthorizedAccessException("Only administrator can delete species");
        }

        return speciesRepository.delete(speciesId);
    }
}
