package com.vsu.app.service;

import com.vsu.app.dto.AnimalScoreDto;
import com.vsu.app.entity.AnimalScore;
import com.vsu.app.entity.Criteria;
import com.vsu.app.entity.Pet;
import com.vsu.app.entity.Profile;
import com.vsu.app.exception.IncorrectAttributeException;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.AnimalScoreRepository;
import com.vsu.app.repository.ExhibitionCriteriaRepository;
import com.vsu.app.repository.ExhibitionPetRepository;
import com.vsu.app.repository.JudgeRepository;
import com.vsu.app.request.CreateScoreRequest;
import com.vsu.app.utils.AnimalScoreMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnimalScoreService {
    private AnimalScoreRepository animalScoreRepository;
    private JudgeRepository judgeRepository;
    private AnimalScoreMappingUtils animalScoreMappingUtils;
    private ExhibitionPetRepository exhibitionPetRepository;
    private ExhibitionCriteriaRepository exhibitionCriteriaRepository;

    public AnimalScoreDto getScore(Long judgeId, Long scoreId) throws UnauthorizedAccessException {
        Long exhibitionId = animalScoreRepository.getById(scoreId).getExhibition_id();
        checkJudgeInExhibition(judgeId, exhibitionId);

        return animalScoreMappingUtils.toDto(animalScoreRepository.getById(scoreId));
    }
    public List<AnimalScoreDto> getAllScores(Long judgeId) {
        return animalScoreRepository.getByJudgeId(judgeId).stream()
                .map(score -> animalScoreMappingUtils.toDto(score))
                .collect(Collectors.toList());
    }

    public Long addScore(Long judgeId, CreateScoreRequest request) throws UnauthorizedAccessException, IncorrectAttributeException {
        checkJudgeInExhibition(judgeId,request.getExhibition_id());
        checkPetInExhibition(request.getPet_id(), request.getExhibition_id());
        checkCriteriaAndScoreInExhibition(request.getCriteria_id(), request.getScore(), request.getExhibition_id());

        AnimalScore score = AnimalScore.builder()
                .pet_id(request.getPet_id())
                .exhibition_id(request.getExhibition_id())
                .judge_id(judgeId)
                .criteria_id(request.getCriteria_id())
                .score(request.getScore())
                .build();
        return animalScoreRepository.insert(score);
    }

    public void updateScore(Long judgeId, Long scoreId, Byte newScore) throws UnauthorizedAccessException, IncorrectAttributeException {

        Long exhibitionId = animalScoreRepository.getById(scoreId).getExhibition_id();
        checkJudgeInExhibition(judgeId, exhibitionId);
        Long criteriaId = animalScoreRepository.getById(scoreId).getCriteria_id();
        checkCriteriaAndScoreInExhibition(criteriaId, newScore, exhibitionId);

        animalScoreRepository.update(scoreId, newScore);
    }

    public void deleteScore(Long judgeId, Long scoreId) throws UnauthorizedAccessException {
        Long exhibitionId = animalScoreRepository.getById(scoreId).getExhibition_id();
        checkJudgeInExhibition(judgeId, exhibitionId);

        animalScoreRepository.delete(scoreId);
    }

    private void checkJudgeInExhibition(Long judgeId, Long exhibitionId) throws UnauthorizedAccessException {
        List<Long> judges = judgeRepository.getAll(exhibitionId).stream()
                .map(Profile::getId)
                .collect(Collectors.toList());
        if (!judges.contains(judgeId)){
            throw new UnauthorizedAccessException("This profile is not a judge at the specified exhibition");
        }
    }

    private void checkPetInExhibition(Long petId, Long exhibitionId) throws IncorrectAttributeException {
        List<Long> pets = exhibitionPetRepository.getAll(exhibitionId).stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
        if (!pets.contains(petId)){
            throw new IncorrectAttributeException("This pet does not participate in the specified exhibition");
        }
    }

    private void checkCriteriaAndScoreInExhibition(Long criteriaId, Byte score, Long exhibitionId) throws IncorrectAttributeException {
        Map<Long, Byte> criteriaAndMaxScores = exhibitionCriteriaRepository.getAll(exhibitionId).stream()
                .collect(Collectors.toMap(Criteria::getId, Criteria::getMaxScore));
        if(!criteriaAndMaxScores.containsKey(criteriaId)){
            throw new IncorrectAttributeException("This criteria are not evaluated at the specified exhibition");
        }
        if(score > criteriaAndMaxScores.get(criteriaId)){
            throw new IncorrectAttributeException("The score exceeds the maximum score according to this criteria");
        }
        if (score < 0){
            throw new IncorrectAttributeException("The score must be greater than or equal to 0");
        }
    }
}
