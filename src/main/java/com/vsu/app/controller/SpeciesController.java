package com.vsu.app.controller;

import com.vsu.app.service.SpeciesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class SpeciesController {
    private SpeciesService speciesService;

    @GetMapping("/species")
    public List<String> getAllSpecies(){
        return speciesService.getAll();
    }
}
