package com.vsu.app.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnimalScore {
    private Long id;
    private Long pet_id;
    private Long exhibition_id;
    private Long judge_id;
    private Long criteria_id;
    private byte score;
}
