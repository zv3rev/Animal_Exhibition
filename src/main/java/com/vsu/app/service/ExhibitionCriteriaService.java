package com.vsu.app.service;

import com.vsu.app.dto.CriteriaDto;
import com.vsu.app.entity.Criteria;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.ExhibitionCriteriaRepository;
import com.vsu.app.utils.CriteriaMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExhibitionCriteriaService {
    private ExhibitionCriteriaRepository exhibitionCriteriaRepository;
    private CriteriaMappingUtils criteriaMappingUtils;
    private ProfileService profileService;

    public void addCriteriа(Long adminId, Long exhibitionId, Long criteriaId) throws UnauthorizedAccessException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can get list of judges");
        }

        exhibitionCriteriaRepository.add(exhibitionId,criteriaId);
    }

    public void deleteCriteriа(Long adminId, Long exhibitionId, Long criteriaId) throws UnauthorizedAccessException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can get list of judges");
        }

        exhibitionCriteriaRepository.delete(exhibitionId,criteriaId);
    }

    public List<CriteriaDto> getAllCriteria(Long adminId, Long exhibitionId) throws UnauthorizedAccessException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can get list of judges");
        }

        List<Criteria> criteria = exhibitionCriteriaRepository.getAll(exhibitionId);

        return criteria.stream().map(c -> criteriaMappingUtils.mapToDto(c)).collect(Collectors.toList());
    }


}
