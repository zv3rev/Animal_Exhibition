package com.vsu.app.controller;

import com.vsu.app.dto.CriteriaDto;
import com.vsu.app.entity.Criteria;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.service.ExhibitionCriteriaService;
import com.vsu.app.utils.CriteriaMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admins/{adminId}/exhibitions/{exhibitionId}/criteria")
@AllArgsConstructor
public class AdminExhibitionCriteriaController {

    private final ExhibitionCriteriaService exhibitionCriteriaService;

    @GetMapping
    public ResponseEntity<List<CriteriaDto>> getAllCriteria(@PathVariable("adminId") Long adminId,
                                                            @PathVariable("exhibitionId") Long exhibitionId) throws UnauthorizedAccessException {
        List<CriteriaDto> criteria = exhibitionCriteriaService.getAllCriteria(adminId, exhibitionId);
        return ResponseEntity.ok(criteria);
    }

    @PostMapping("/{criteriаId}")
    public ResponseEntity<Void> addCriteria(@PathVariable("adminId") Long adminId,
                                            @PathVariable("exhibitionId") Long exhibitionId,
                                            @PathVariable("criteriаId") Long criteriaId) throws UnauthorizedAccessException {
        exhibitionCriteriaService.addCriteriа(adminId, exhibitionId, criteriaId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{criteriaId}")
    public ResponseEntity<Void> deleteCriteria(@PathVariable("adminId") Long adminId,
                                               @PathVariable("exhibitionId") Long exhibitionId,
                                               @PathVariable("criteriaId") Long criteriaId) throws UnauthorizedAccessException {
        exhibitionCriteriaService.deleteCriteriа(adminId, exhibitionId, criteriaId);
        return ResponseEntity.ok().build();
    }
}
