package com.vsu.app.controller;

import com.vsu.app.dto.ExhibitionDto;
import com.vsu.app.service.ExhibitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/exhibitions")
public class ExhibitionController {
    private ExhibitionService exhibitionService;

    @GetMapping
    public List<ExhibitionDto> getAllExhibitions(){
        throw new NotImplementedException();
    }

    @GetMapping
    public List<ExhibitionDto> getAllExhibitionsInSpecies(@RequestParam("speciesId") Long speciesId){
        throw new NotImplementedException();
    }

    @PostMapping("/{exhibitionId}")
    public ResponseEntity registerForExhibition(@PathVariable("exhibitionId") Long exhibitionId, @RequestParam("userId") Long userId, @RequestParam("petId") Long petId){
        throw new NotImplementedException();
    }
}
