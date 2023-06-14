package com.vsu.app.utils;

import com.vsu.app.dto.ExhibitionDto;
import com.vsu.app.entity.Exhibition;
import com.vsu.app.request.CreateExhibitionRequest;
import com.vsu.app.request.EditExhibitionRequest;
import org.springframework.stereotype.Component;

@Component
public class ExhibitionMappingUtils {
    public Exhibition mapToEntity(CreateExhibitionRequest request){
        return Exhibition.builder()
                .name(request.getName())
                .description(request.getDescription())
                .start(request.getStart())
                .end(request.getEnd())
                .speciesId(request.getSpeciesId())
                .enterPrice(request.getEnterPrice()).build();
    }

    public Exhibition mapToEntity(EditExhibitionRequest request) {
        return Exhibition.builder()
                .name(request.getName())
                .description(request.getDescription())
                .start(request.getStart())
                .end(request.getEnd())
                .speciesId(request.getSpeciesId())
                .enterPrice(request.getEnterPrice()).build();
    }

    public ExhibitionDto mapToDto(Exhibition exhibition) {
        return new ExhibitionDto(
                exhibition.getId(),
                exhibition.getName(),
                exhibition.getDescription(),
                exhibition.getStart(),
                exhibition.getEnd(),
                exhibition.getSpeciesId(),
                exhibition.getEnterPrice());
    }
}
