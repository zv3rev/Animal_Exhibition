package com.vsu.app.dto;

import com.vsu.app.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    Long id;
    String username;
    String fio;
    String email;
    Long phone;
    Role role;
}
