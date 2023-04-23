package com.vsu.app.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class Exhibition {
    private Long id;
    private String name;
    private String description;
    private Date start;
    private Date end;
    private Long species_id;
    private Double enter_price;
}
