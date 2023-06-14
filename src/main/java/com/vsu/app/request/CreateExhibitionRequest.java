package com.vsu.app.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Date;

@Data
public class CreateExhibitionRequest {
    @NotBlank(message = "Exhibition name can't be blank")
    private String name;
    @NotNull(message = "Exhibition description can't be null")
    private String description;
    @Future(message = "Exhibition should starts in the future")
    private Date start;
    @Future( message = "Exhibition should ends in the future")
    private Date end;
    @Positive(message = "Species id can't be negative")
    private Long speciesId;
    @PositiveOrZero(message = "Entry price can't be negative")
    private Double enterPrice;
}
