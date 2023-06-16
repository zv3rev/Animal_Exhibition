package com.vsu.app.service;

import com.vsu.app.dto.ProfileDto;
import com.vsu.app.entity.Profile;
import com.vsu.app.exception.IncorrectAttributeException;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.JudgeRepository;
import com.vsu.app.utils.ProfileMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JudgeService {
    private JudgeRepository judgeRepository;
    private ProfileService profileService;
    private ProfileMappingUtils profileMappingUtils;
    public List<ProfileDto> getAllJudges(Long adminId, Long exhibitionId) throws UnauthorizedAccessException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can get list of judges");
        }

        List<Profile> judges = judgeRepository.getAll(exhibitionId);
        return judges.stream()
                .map(judge -> profileMappingUtils.mapToProfileDto(judge))
                .collect(Collectors.toList());
    }

    public void addJudge(Long adminId, Long exhibitionId, Long judgeId) throws UnauthorizedAccessException, IncorrectAttributeException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can get list of judges");
        }

        if (!profileService.isJudge(judgeId)){
            throw new IncorrectAttributeException("Provided user id don't have judge role");
        }

        judgeRepository.add(exhibitionId,judgeId);
    }

    public void deleteJudge(Long adminId, Long exhibitionId, Long judgeId) throws UnauthorizedAccessException, IncorrectAttributeException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can get list of judges");
        }

        if (!profileService.isJudge(judgeId)){
            throw new IncorrectAttributeException("Provided user id don't have judge role");
        }

        judgeRepository.delete(exhibitionId,judgeId);
    }
}
