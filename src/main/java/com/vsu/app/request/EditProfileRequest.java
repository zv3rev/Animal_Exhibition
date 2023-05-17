package com.vsu.app.request;

import com.vsu.app.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class EditProfileRequest {
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Length of the password must be between 6 and 100")
    private String password;

    @NotBlank(message = "Name is required")
    private String fio;

    @Email(message = "Incorrect email format")
    private String email;

    @Range(min = 10000000000L, max = 99999999999L, message = "Phone number must contain 11 digits")
    private Long phone;

    @NotNull
    private Role role;
}
