package com.vsu.app.controller;

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

}
