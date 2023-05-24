package com.vsu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class PetDto {
    private Long id;
    private Long ownerId;
    private Long breedId;
    private String nickname;
    private Date birthday;
}
