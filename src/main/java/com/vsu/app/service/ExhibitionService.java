package com.vsu.app.service;

import com.vsu.app.dto.ExhibitionDto;
import com.vsu.app.entity.Exhibition;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.ExhibitionRepository;
import com.vsu.app.request.CreateExhibitionRequest;
import com.vsu.app.request.EditExhibitionRequest;
import com.vsu.app.utils.ExhibitionMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExhibitionService {
    private ExhibitionRepository exhibitionRepository;
    private ProfileService profileService;
    private ExhibitionMappingUtils exhibitionMappingUtils;

    public Long addExhibition(Long adminId, CreateExhibitionRequest request) throws UnauthorizedAccessException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can add exhibition");
        }
        return exhibitionRepository.create(exhibitionMappingUtils.mapToEntity(request));
    }

    public void deleteExhibition(Long adminId, Long exhibitionId) throws UnauthorizedAccessException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can delete exhibition");
        }
        exhibitionRepository.delete(exhibitionId);
    }

    public void updateExhibition(Long adminId, Long exhibitionId, EditExhibitionRequest request) throws UnauthorizedAccessException {
        if (!profileService.isAdmin(adminId)){
            throw new UnauthorizedAccessException("Only admin can delete exhibition");
        }

        Exhibition  exhibitionToUpdate = exhibitionMappingUtils.mapToEntity(request);

        exhibitionRepository.update(exhibitionId,exhibitionToUpdate);
    }

    public ExhibitionDto getExhibitionById(Long exhibitionId) {
        Exhibition result = exhibitionRepository.getById(exhibitionId);
        return exhibitionMappingUtils.mapToDto(result);
    }

    public List<ExhibitionDto> getAllExhibitions() {
        List<Exhibition> result = exhibitionRepository.getAll();
        return result.stream()
                .map(exhibition -> exhibitionMappingUtils.mapToDto(exhibition))
                .collect(Collectors.toList());
    }

    public List<ExhibitionDto> getExhibitionsBySpeciesId(Long speciesId) {
        List<Exhibition> result = exhibitionRepository.getBySpeciesId(speciesId);
        return result.stream()
                .map(exhibition -> exhibitionMappingUtils.mapToDto(exhibition))
                .collect(Collectors.toList());
    }
}
