package com.vsu.app.controller;

import com.vsu.app.dto.PetDto;
import com.vsu.app.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}/pets")
public class PetController {

    private PetService petService;
    @GetMapping
    public List<PetDto> getAllPets(@PathVariable("userId") Long userId){
        return petService.getUserPets(userId);
    }
}
