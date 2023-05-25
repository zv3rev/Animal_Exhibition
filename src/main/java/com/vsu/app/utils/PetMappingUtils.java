package com.vsu.app.utils;

import com.vsu.app.dto.PetDto;
import com.vsu.app.entity.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetMappingUtils {
    public PetDto toDto(Pet pet){
        return new PetDto(
                pet.getId(),
                pet.getOwner_id(),
                pet.getBreed_id(),
                pet.getNickname(),
                pet.getBirthday());
    }
}
