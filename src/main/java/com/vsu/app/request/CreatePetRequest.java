package com.vsu.app.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.sql.Date;

@Data
public class CreatePetRequest {
    @Positive(message = "Breed id must be positive number")
    private Long breedId;
    @NotBlank(message = "Pet nickname can't be blank")
    private String nickname;
    @Past(message = "Date of pet's birth must be earlier than present date")
    private Date birthday;
}
