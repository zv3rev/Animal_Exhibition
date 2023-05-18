package com.vsu.app.controller;

import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.request.CreateCriteriaRequest;
import com.vsu.app.service.CriteriaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/admins/{adminId}/criteria")
public class CriteriaController {
    private CriteriaService criteriaService;

    @PostMapping
    public boolean addCriteria(@PathVariable("adminId") Long adminId, @RequestBody @Valid CreateCriteriaRequest createCriteriaRequest) throws UnauthorizedAccessException {
        return criteriaService.add(adminId,createCriteriaRequest);
    }

    @DeleteMapping("/{criteriaId}")
    public boolean deleteCriteria(@PathVariable("adminId") Long adminId, @PathVariable("criteriaId") Long criteriaId) throws UnauthorizedAccessException {
        return criteriaService.delete(adminId, criteriaId);
    }

}
