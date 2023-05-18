package com.vsu.app.service;

import com.vsu.app.dto.CriteriaDto;
import com.vsu.app.entity.Criteria;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.CriteriaRepository;
import com.vsu.app.request.CreateCriteriaRequest;
import com.vsu.app.request.EditCriteriaRequest;
import com.vsu.app.utilities.CriteriaMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CriteriaService {
    private CriteriaRepository criteriaRepository;
    private CriteriaMappingUtils criteriaMappingUtils;
    private ProfileService profileService;

    public boolean add(Long adminId, CreateCriteriaRequest request) throws UnauthorizedAccessException {
        if(!profileService.checkAdminRole(adminId)){
            throw new UnauthorizedAccessException("Only admin can add criteria");
        }
        return criteriaRepository.create(criteriaMappingUtils.mapToEntity(request));
    }

    public boolean delete(Long adminId, Long criteriaId) throws UnauthorizedAccessException {
        if (!profileService.checkAdminRole(adminId)){
            throw new UnauthorizedAccessException("Only admin can delete criteria");
        }

        return criteriaRepository.delete(criteriaId);
    }

    public CriteriaDto edit(Long adminId, Long criteriaId, EditCriteriaRequest editCriteriaRequest) throws UnauthorizedAccessException {
        if (!profileService.checkAdminRole(adminId)){
            throw new UnauthorizedAccessException("Only admin can delete criteria ");
        }

        Criteria criteriaToEdit = Criteria.builder()
                .id(criteriaId)
                .name(editCriteriaRequest.getName())
                .description(editCriteriaRequest.getDescription())
                .maxScore(editCriteriaRequest.getMaxScore())
                .build();
        if (criteriaRepository.update(criteriaToEdit)){
            return criteriaMappingUtils.mapToDto(criteriaToEdit);
        }
            return criteriaMappingUtils.mapToDto(criteriaRepository.get(criteriaId));

    }
}
