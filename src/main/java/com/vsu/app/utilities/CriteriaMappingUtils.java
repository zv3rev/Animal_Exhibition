package com.vsu.app.utilities;

import com.vsu.app.entity.Criteria;
import com.vsu.app.request.CreateCriteriaRequest;
import org.springframework.stereotype.Component;

@Component
public class CriteriaMappingUtils {
    public Criteria mapToCriteria(CreateCriteriaRequest request){
        return Criteria.builder()
                .name(request.getName())
                .description(request.getDescription())
                .maxScore(request.getMaxScore())
                .build();
    }
}
