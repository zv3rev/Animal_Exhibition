package com.vsu.app.utilities;

import com.vsu.app.dto.CriteriaDto;
import com.vsu.app.entity.Criteria;
import com.vsu.app.request.CreateCriteriaRequest;
import org.springframework.stereotype.Component;

@Component
public class CriteriaMappingUtils {
    public Criteria mapToEntity(CreateCriteriaRequest request){
        return Criteria.builder()
                .name(request.getName())
                .description(request.getDescription())
                .maxScore(request.getMaxScore())
                .build();
    }

    public CriteriaDto mapToDto(Criteria criteria) {
        return CriteriaDto.builder()
                .name(criteria.getName())
                .description(criteria.getDescription())
                .maxScore(criteria.getMaxScore())
                .build();
    }
}
