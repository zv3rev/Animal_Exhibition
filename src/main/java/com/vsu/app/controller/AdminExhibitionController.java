package com.vsu.app.controller;

import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.request.CreateExhibitionRequest;
import com.vsu.app.request.EditExhibitionRequest;
import com.vsu.app.service.ExhibitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/admins/{adminId}/exhibitions")
@AllArgsConstructor
public class AdminExhibitionController {

    private final ExhibitionService exhibitionService;

    @PostMapping
    public ResponseEntity<Void> addExhibition(@PathVariable("adminId") Long adminId,
                                              @RequestBody CreateExhibitionRequest request) throws UnauthorizedAccessException {
        Long exhibitionId = exhibitionService.addExhibition(adminId, request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{exhibitionId}")
                .buildAndExpand(exhibitionId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{exhibitionId}")
    public ResponseEntity<Void> deleteExhibition(@PathVariable("adminId") Long adminId,
                                                 @PathVariable("exhibitionId") Long exhibitionId) throws UnauthorizedAccessException {
        exhibitionService.deleteExhibition(adminId, exhibitionId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{exhibitionId}")
    public ResponseEntity<Void> updateExhibition(@PathVariable("adminId") Long adminId,
                                                 @PathVariable("exhibitionId") Long exhibitionId,
                                                 @RequestBody EditExhibitionRequest request) throws UnauthorizedAccessException {
        exhibitionService.updateExhibition(adminId, exhibitionId, request);
        return ResponseEntity.noContent().build();
    }
}
