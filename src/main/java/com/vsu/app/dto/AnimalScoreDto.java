package com.vsu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnimalScoreDto {
    private Long id;
    private Long pet_id;
    private Long exhibition_id;
    private Long judge_id;
    private Long criteria_id;
    private byte score;
}
