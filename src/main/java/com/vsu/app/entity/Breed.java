package com.vsu.app.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Breed {
    private Long id;
    private String name;
    private Long species_id;
}
