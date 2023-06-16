package com.vsu.app.controller;

import com.vsu.app.service.BreedService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/species/{speciesId}/breeds")
public class BreedController {
    private BreedService breedService;

    @GetMapping
    public List<String> getAllBreedsInSpecies(@PathVariable("speciesId") Long  species_id){
        return breedService.getAllinSpecies(species_id);
    }
}
