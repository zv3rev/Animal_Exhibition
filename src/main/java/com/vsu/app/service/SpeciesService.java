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

    public boolean add(Long admin_id, String name) throws UnauthorizedAccessException {
        if (!profileService.checkAdminRole(admin_id)){
            throw new UnauthorizedAccessException("Only administrator can add species");
        }
        return  speciesRepository.create(name);
    }
}
