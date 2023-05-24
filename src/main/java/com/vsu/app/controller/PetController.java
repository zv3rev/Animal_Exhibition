package com.vsu.app.controller;

import com.vsu.app.dto.PetDto;
import com.vsu.app.request.CreatePetRequest;
import com.vsu.app.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}/pets")
public class PetController {

    private PetService petService;

    @GetMapping
    public List<PetDto> getAllPets(@PathVariable("userId") Long userId) {
        return petService.getUserPets(userId);
    }

    @PostMapping
    public ResponseEntity addPet(@PathVariable("userId") Long userId, @RequestBody @Valid CreatePetRequest createPetRequest) {
        Long createdId = petService.add(userId, createPetRequest);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(createdId)
                                .toUri())
                .build();
    }
}
