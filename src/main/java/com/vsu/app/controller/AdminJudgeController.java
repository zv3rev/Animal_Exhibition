package com.vsu.app.controller;

import com.vsu.app.dto.ProfileDto;
import com.vsu.app.exception.IncorrectAttributeException;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.service.JudgeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins/{adminId}/exhibitions/{exhibitionId}/judges")
@AllArgsConstructor
public class AdminJudgeController {

    private final JudgeService judgeService;

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllJudges(@PathVariable("adminId") Long adminId,
                                                         @PathVariable("exhibitionId") Long exhibitionId) throws UnauthorizedAccessException {
        List<ProfileDto> judges = judgeService.getAllJudges(adminId, exhibitionId);
        return ResponseEntity.ok(judges);
    }

    @PostMapping("/{judgeId}")
    public ResponseEntity<Void> addJudge(@PathVariable("adminId") Long adminId,
                                         @PathVariable("exhibitionId") Long exhibitionId,
                                         @PathVariable("judgeId") Long judgeId) throws UnauthorizedAccessException, IncorrectAttributeException {
        judgeService.addJudge(adminId, exhibitionId, judgeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{judgeId}")
    public ResponseEntity<Void> deleteJudge(@PathVariable("adminId") Long adminId,
                                            @PathVariable("exhibitionId") Long exhibitionId,
                                            @PathVariable("judgeId") Long judgeId) throws UnauthorizedAccessException, IncorrectAttributeException {
        judgeService.deleteJudge(adminId, exhibitionId, judgeId);
        return ResponseEntity.noContent().build();
    }
}
