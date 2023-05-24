package com.vsu.app.service;

import com.vsu.app.entity.Breed;
import com.vsu.app.exception.IncorrectAttributeException;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.BreedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BreedService {
    private BreedRepository breedRepository;
    private ProfileService profileService;

    public boolean add(Long adminId, Long speciesId, String breedName) throws UnauthorizedAccessException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can add breeds");
        }
        return breedRepository.create(speciesId,breedName);
    }

    public boolean delete(Long adminId, Long speciesId, Long breedId) throws UnauthorizedAccessException, IncorrectAttributeException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can delete breeds");
        }
        Breed breed = breedRepository.getById(breedId);
        if (!Objects.equals(speciesId, breed.getSpecies_id())){
            throw new IncorrectAttributeException("This species does not contain the specified breed");
        }

        return breedRepository.delete(breedId);
    }

    public List<String> getAllinSpecies(Long speciesId) {
        return  breedRepository.getBySpecies(speciesId).stream().map(Breed::getName).collect(Collectors.toList());
    }
}
