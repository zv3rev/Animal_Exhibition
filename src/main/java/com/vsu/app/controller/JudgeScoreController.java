package com.vsu.app.controller;

import com.vsu.app.dto.AnimalScoreDto;
import com.vsu.app.exception.IncorrectAttributeException;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.request.CreateScoreRequest;
import com.vsu.app.service.AnimalScoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/judges/{judgeId}/scores")
@AllArgsConstructor
public class JudgeScoreController {

    private final AnimalScoreService animalScoreService;

    @GetMapping("/{scoreId}")
    public ResponseEntity<AnimalScoreDto> getScore(@PathVariable("judgeId") Long judgeId,
                                          @PathVariable("scoreId") Long scoreId) throws UnauthorizedAccessException {
        AnimalScoreDto score = animalScoreService.getScore(judgeId, scoreId);
        return ResponseEntity.ok(score);
    }

    @GetMapping
    public ResponseEntity<List<AnimalScoreDto>> getAllScores(@PathVariable("judgeId") Long judgeId) {
        List<AnimalScoreDto> scores = animalScoreService.getAllScores(judgeId);
        return ResponseEntity.ok(scores);
    }

    @PostMapping
    public ResponseEntity<Void> addScore(@PathVariable("judgeId") Long judgeId,
                                         @RequestBody @Valid CreateScoreRequest request) throws UnauthorizedAccessException, IncorrectAttributeException {
        Long scoreId = animalScoreService.addScore(judgeId, request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{scoreId}")
                .buildAndExpand(scoreId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{scoreId}")
    public ResponseEntity<Void> updateScore(@PathVariable("judgeId") Long judgeId,
                                            @PathVariable("scoreId") Long scoreId,
                                            @RequestParam("newScore") Byte newScore) throws UnauthorizedAccessException, IncorrectAttributeException {
        animalScoreService.updateScore(judgeId, scoreId, newScore);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{scoreId}")
    public ResponseEntity<Void> deleteScore(@PathVariable("judgeId") Long judgeId,
                                            @PathVariable("scoreId") Long scoreId) throws UnauthorizedAccessException {
        animalScoreService.deleteScore(judgeId, scoreId);
        return ResponseEntity.ok().build();
    }

}
