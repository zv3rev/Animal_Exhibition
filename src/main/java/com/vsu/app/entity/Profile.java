package com.vsu.app.entity;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class Profile {
    private Long id;
    private String username;
    private String password;
    private String fio;
    private String email;
    private Long phone;
    private Role role;
}
