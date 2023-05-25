package com.vsu.app.controller;

import com.vsu.app.dto.ExhibitionDto;
import com.vsu.app.request.CreateExhibitionRequest;
import com.vsu.app.request.EditExhibitionRequest;
import com.vsu.app.service.ExhibitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/admins/{adminId}/exhibitions")
public class AdminExhibitionController {
    private ExhibitionService exhibitionService;

    @PostMapping
    public ResponseEntity addExhibition(@PathVariable("adminId") Long adminId, @RequestBody @Valid CreateExhibitionRequest request){
        throw new NotImplementedException();
    }

    @DeleteMapping("/{exhibitionId}")
    public boolean deleteExhibition(@PathVariable("adminId") Long adminId, @PathVariable("exhibitionId") Long exhibitionId){
        throw new NotImplementedException();
    }

    @PutMapping("/{exhibitionId}")
    public ExhibitionDto editExhibition(@PathVariable("adminId") Long adminId, @PathVariable("exhibitionId") Long exhibitionId, @RequestBody @Valid EditExhibitionRequest request){
        throw new NotImplementedException();
    }
}
