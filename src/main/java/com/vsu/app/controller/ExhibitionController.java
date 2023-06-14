package com.vsu.app.controller;

import com.vsu.app.dto.ExhibitionDto;
import com.vsu.app.service.ExhibitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exhibitions")
@AllArgsConstructor
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    @GetMapping("/{exhibitionId}")
    public ResponseEntity<ExhibitionDto> getExhibitionById(@PathVariable("exhibitionId") Long exhibitionId) {
        ExhibitionDto exhibition = exhibitionService.getExhibitionById(exhibitionId);
        return ResponseEntity.ok(exhibition);
    }

    @GetMapping
    public ResponseEntity<List<ExhibitionDto>> getAllExhibitions() {
        List<ExhibitionDto> exhibitions = exhibitionService.getAllExhibitions();
        return ResponseEntity.ok(exhibitions);
    }

    @GetMapping(params = "speciesId")
    public ResponseEntity<List<ExhibitionDto>> getExhibitionsBySpeciesId(@RequestParam("speciesId") Long speciesId) {
        List<ExhibitionDto> exhibitions = exhibitionService.getExhibitionsBySpeciesId(speciesId);
        return ResponseEntity.ok(exhibitions);
    }
}
