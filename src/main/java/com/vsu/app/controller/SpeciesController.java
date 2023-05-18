package com.vsu.app.controller;

import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.service.SpeciesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class SpeciesController {
    private SpeciesService speciesService;

    //TODO: добавить обработку повторяющихся значений
    @PostMapping ("/admin/{adminId}/species")
    public boolean addSpecies(@PathVariable("adminId") Long adminId, @RequestParam String speciesName) throws UnauthorizedAccessException {
        return speciesService.add(adminId,speciesName);
    }

    @DeleteMapping("/admin/{adminId}/species/{speciesId}")
    public boolean deleteSpecies(@PathVariable("adminId") Long adminId, @PathVariable("speciesId") Long speciesId) throws UnauthorizedAccessException {
        return speciesService.delete(adminId, speciesId);
    }

    @GetMapping("/species")
    public List<String> getAllSpecies(){
        return speciesService.getAll();
    }
}
