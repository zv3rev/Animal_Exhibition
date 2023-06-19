package com.vsu.app.service;

import com.vsu.app.dto.PetDto;
import com.vsu.app.entity.Pet;
import com.vsu.app.exception.IncorrectAttributeException;
import com.vsu.app.repository.ExhibitionPetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ExhibitionPetService {
    private ExhibitionPetRepository exhibitionPetRepository;
    private ExhibitionService exhibitionService;
    private PetService petService;
    private BreedService breedService;

    public void addPet(Long userId, Long exhibitionId, Long petId) throws IncorrectAttributeException {
        validateAttributes(userId, exhibitionId, petId);
        exhibitionPetRepository.add(exhibitionId,petId);
    }

    public void deletePet(Long userId, Long exhibitionId, Long petId) throws IncorrectAttributeException {
        validateAttributes(userId, exhibitionId, petId);
        exhibitionPetRepository.delete(exhibitionId, petId);
    }

    private void validateAttributes(Long userId, Long exhibitionId, Long petId) throws IncorrectAttributeException {
        PetDto pet = petService.getById(petId);
        Long petSpeciesId = breedService.getById(pet.getBreedId()).getSpecies_id();
        Long exhibitionSpeciesId = exhibitionService.getExhibitionById(exhibitionId).getSpecies_id();

        if (!Objects.equals(petSpeciesId, exhibitionSpeciesId)){
            throw new IncorrectAttributeException("The species of animal is different from the species of animals at the exhibition");
        }

        if (!Objects.equals(pet.getOwnerId(), userId)){
            throw new IncorrectAttributeException("This user does not own the specified animal");
        }
    }
}
