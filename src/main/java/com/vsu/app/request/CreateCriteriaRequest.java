package com.vsu.app.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateCriteriaRequest {
    @NotBlank(message = "Name can't be blank")
    private String name;
    @NotNull(message = "Description can't be null")
    private String description;
    @Range(min = 1, max = 100, message = "Maximum score must be between 1 and 100")
    private Byte maxScore;
}
