package com.vsu.app.service;

import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.BreedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BreedService {
    private BreedRepository breedRepository;
    private ProfileService profileService;

    public boolean add(Long adminId, Long speciesId, String breedName) throws UnauthorizedAccessException {
        if (!profileService.checkAdminRole(adminId)){
            throw new UnauthorizedAccessException("Only admin can add breeds");
        }

        return breedRepository.create(speciesId,breedName);
    }
}
