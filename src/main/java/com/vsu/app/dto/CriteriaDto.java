package com.vsu.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriteriaDto {
    private String name;
    private String description;
    private Byte maxScore;
}
