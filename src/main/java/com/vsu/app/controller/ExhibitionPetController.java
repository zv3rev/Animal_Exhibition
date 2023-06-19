package com.vsu.app.controller;

import com.vsu.app.exception.IncorrectAttributeException;
import com.vsu.app.service.ExhibitionPetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/exhibitions/{exhibitionId}/pets/{petId}")
@AllArgsConstructor
public class ExhibitionPetController {

    private final ExhibitionPetService exhibitionPetService;

    @PostMapping
    public ResponseEntity<Void> addPet(@PathVariable("userId") Long userId,
                                       @PathVariable("exhibitionId") Long exhibitionId,
                                       @PathVariable("petId") Long petId) throws IncorrectAttributeException {
        exhibitionPetService.addPet(userId, exhibitionId, petId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePet(@PathVariable("userId") Long userId,
                                          @PathVariable("exhibitionId") Long exhibitionId,
                                          @PathVariable("petId") Long petId) throws IncorrectAttributeException {
        exhibitionPetService.deletePet(userId, exhibitionId, petId);
        return ResponseEntity.ok().build();
    }
}