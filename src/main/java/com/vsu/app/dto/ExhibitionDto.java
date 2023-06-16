package com.vsu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ExhibitionDto {
    private Long id;
    private String name;
    private String description;
    private Date start;
    private Date end;
    private Long species_id;
    private Double enter_price;
}
