package com.vsu.app.controller;

import com.vsu.app.exception.IncorrectAttributeException;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.service.BreedService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/{adminId}/species/{speciesId}/breeds")
public class AdminBreedController {
    private BreedService breedService;

    @PostMapping
    public boolean addBreed(@PathVariable("adminId") Long adminId, @PathVariable("speciesId") Long speciesId, @RequestParam("breedName") String breedName) throws UnauthorizedAccessException {
        return breedService.add(adminId,speciesId,breedName);
    }

    @DeleteMapping ("/{breedId}")
    public boolean deleteBreed(@PathVariable("adminId") Long adminId, @PathVariable("speciesId") Long speciesId, @PathVariable("breedId") Long breedId) throws IncorrectAttributeException, UnauthorizedAccessException {
        return  breedService.delete(adminId,speciesId,breedId);
    }

}
