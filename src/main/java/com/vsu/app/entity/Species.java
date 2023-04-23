package com.vsu.app.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Species {
    private Long id;
    private String name;
}
