package com.vsu.app.service;

import com.vsu.app.entity.Profile;
import com.vsu.app.entity.Role;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.CriteriaRepository;
import com.vsu.app.repository.ProfileRepository;
import com.vsu.app.request.CreateCriteriaRequest;
import com.vsu.app.utilities.CriteriaMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CriteriaService {
    private CriteriaRepository criteriaRepository;
    private CriteriaMappingUtils criteriaMappingUtils;
    private ProfileRepository profileRepository;

    public boolean add(Long adminId, CreateCriteriaRequest request) throws UnauthorizedAccessException {
        if(!checkAdminRole(adminId)){
            throw new UnauthorizedAccessException("Only admin can add criteria");
        }
        return criteriaRepository.create(criteriaMappingUtils.mapToCriteria(request));
    }

    private boolean checkAdminRole(Long admin_id){
        Profile admin = profileRepository.getById(admin_id);
        return admin != null && admin.getRole() == Role.ADMINISTRATOR;
    }
}
