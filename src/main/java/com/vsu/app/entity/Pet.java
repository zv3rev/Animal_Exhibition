package com.vsu.app.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class Pet {
    private Long id;
    private Long owner_id;
    private Long breed_id;
    private String nickname;
    private Date birthday;
}
