package com.vsu.app.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EditCriteriaRequest {
    @NotBlank(message = "Name can't be blank")
    @Length(max = 30, message = "Maximum name length is 30")
    private String name;
    @NotNull(message = "Description can't be null")
    private String description;
    @Range(min = 1, max = 100, message = "Maximum score must be between 1 and 100")
    private Byte maxScore;
}