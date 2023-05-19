package com.vsu.app.controller;

import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.service.SpeciesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/{adminId}/species")
public class AdminSpeciesController {
    private SpeciesService speciesService;

    //TODO: добавить обработку повторяющихся значений
    @PostMapping
    public boolean addSpecies(@PathVariable("adminId") Long adminId, @RequestParam String speciesName) throws UnauthorizedAccessException {
        return speciesService.add(adminId,speciesName);
    }

    @DeleteMapping("/{speciesId}")
    public boolean deleteSpecies(@PathVariable("adminId") Long adminId, @PathVariable("speciesId") Long speciesId) throws UnauthorizedAccessException {
        return speciesService.delete(adminId, speciesId);
    }
}
