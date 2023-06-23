package com.vsu.app.utils;

import com.vsu.app.dto.AnimalScoreDto;
import com.vsu.app.entity.AnimalScore;
import org.springframework.stereotype.Component;

@Component
public class AnimalScoreMappingUtils {
    public AnimalScoreDto toDto(AnimalScore animalScore){
        return new AnimalScoreDto(
                animalScore.getId(),
                animalScore.getPet_id(),
                animalScore.getExhibition_id(),
                animalScore.getJudge_id(),
                animalScore.getCriteria_id(),
                animalScore.getScore());
    }
}
