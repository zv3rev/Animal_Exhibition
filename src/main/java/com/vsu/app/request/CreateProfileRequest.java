package com.vsu.app.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProfileRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 6, max = 100, message = "Length of the username must be between 6 and 100")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Length of the password must be between 6 and 100")
    private String password;

    @NotBlank(message = "Name is required")
    private String fio;

    @Email(message = "Incorrect email format")
    private String email;

    @Min(value = 11, message = "Phone number must contain 11 digits")
    @Digits(integer = 11,fraction = 0, message = "Phone number can contain only numbers")
    private Long phone;
}
