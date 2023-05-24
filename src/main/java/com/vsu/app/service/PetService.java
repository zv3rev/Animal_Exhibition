package com.vsu.app.service;

import com.vsu.app.dto.PetDto;
import com.vsu.app.entity.Pet;
import com.vsu.app.repository.PetRepository;
import com.vsu.app.request.CreatePetRequest;
import com.vsu.app.utils.PetMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PetService {
    private PetRepository petRepository;
    private PetMappingUtils petMappingUtils;

    public List<PetDto> getUserPets(Long userId){
        return petRepository.getByOwnerId(userId).stream()
                .map(pet ->petMappingUtils.toDto(pet))
                .collect(Collectors.toList());
    }

    //TODO: отловить когда не вернется ни одного id
    public Long add(Long userId, CreatePetRequest request){
        Pet petToAdd = Pet.builder()
                .breed_id(request.getBreedId())
                .nickname(request.getNickname())
                .birthday(request.getBirthday())
                .owner_id(userId)
                .build();
        return petRepository.create(petToAdd);
    }
}
