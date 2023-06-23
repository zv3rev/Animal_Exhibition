package com.vsu.app.repository;

import com.vsu.app.entity.AnimalScore;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class AnimalScoreRepository {
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_BY_ID_QUERY = "SELECT id, pet_id, exhibition_id, judge_id, criteria_id, score FROM Animal_Score WHERE id = ?";
    private static final String SELECT_BY_JUDGE_ID_QUERY = "SELECT id, pet_id, exhibition_id, judge_id, criteria_id, score FROM Animal_Score WHERE judge_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO Animal_Score (pet_id, exhibition_id, judge_id, criteria_id, score) VALUES (?, ?, ?, ?, ?) RETURNING id";
    private static final String UPDATE_QUERY = "UPDATE Animal_Score SET score = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Animal_Score WHERE id = ?";

    public AnimalScore getById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, (rs,row) -> mapRowToAnimalScore(rs), id);
    }

    public List<AnimalScore> getByJudgeId(Long judgeId) {
        return jdbcTemplate.query(SELECT_BY_JUDGE_ID_QUERY, (rs,row) -> mapRowToAnimalScore(rs), judgeId);
    }

    public Long insert(AnimalScore animalScore) {
        return jdbcTemplate.queryForObject(INSERT_QUERY, Long.class, animalScore.getPet_id(), animalScore.getExhibition_id(), animalScore.getJudge_id(), animalScore.getCriteria_id(), animalScore.getScore());
    }

    public void update(Long id, Byte score) {
        jdbcTemplate.update(UPDATE_QUERY, score, id);
    }

    public void delete(Long id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    private AnimalScore mapRowToAnimalScore(ResultSet rs) throws SQLException {
        return AnimalScore.builder()
                .id(rs.getLong("id"))
                .pet_id(rs.getLong("pet_id"))
                .exhibition_id(rs.getLong("exhibition_id"))
                .judge_id(rs.getLong("judge_id"))
                .criteria_id(rs.getLong("criteria_id"))
                .score(rs.getByte("score"))
                .build();
    }
}
