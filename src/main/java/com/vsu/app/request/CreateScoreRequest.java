package com.vsu.app.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class CreateScoreRequest {
    @Positive(message = "Pet id must be positive")
    Long pet_id;
    @Positive(message = "Exhibition id must be positive")
    Long exhibition_id;
    @Positive(message = "Criteria id must be positive")
    Long criteria_id;
    @Range(min = 0, max = 100, message = "Score must be between 0 to 100")
    Byte score;
}
