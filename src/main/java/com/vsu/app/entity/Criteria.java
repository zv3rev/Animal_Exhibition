package com.vsu.app.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Criteria {
    private Long id;
    private String name;
    private String description;
    private Byte maxScore;
}
