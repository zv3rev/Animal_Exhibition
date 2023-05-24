package com.vsu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class PetDto {
    private Long id;
    private Long owner_id;
    private Long breed_id;
    private String nickname;
    private Date birthday;
}
