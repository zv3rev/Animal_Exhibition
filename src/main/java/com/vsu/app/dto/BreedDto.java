package com.vsu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BreedDto {
    private Long id;
    private String name;
    private Long species_id;
}
