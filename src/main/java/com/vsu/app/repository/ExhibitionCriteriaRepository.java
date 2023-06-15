package com.vsu.app.repository;

import com.vsu.app.entity.Criteria;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class ExhibitionCriteriaRepository {
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_BY_EXHIBITION_ID_QUERY = "SELECT cae.id, cae.criteria_id, cae.exhibition_id, c.id criteria_id, c.name, c.description, c.maxscore FROM criteria_at_exhibition cae JOIN criteria c ON cae.criteria_id = c.id WHERE cae.exhibition_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO criteria_at_exhibition (criteria_id, exhibition_id) VALUES (?, ?) RETURNING id";
    private static final String DELETE_QUERY = "DELETE FROM criteria_at_exhibition WHERE criteria_id = ? AND exhibition_id = ?";

    public List<Criteria> getAll(Long exhibitionId) {
        return jdbcTemplate.query(SELECT_BY_EXHIBITION_ID_QUERY, (rs,row)->mapRowToCriteriaAtExhibition(rs), exhibitionId);
    }

    public Long add(Long exhibitionId, Long criteriaId) {
        return jdbcTemplate.queryForObject(INSERT_QUERY, Long.class, criteriaId, exhibitionId);
    }

    public void delete(Long exhibitionId, Long criteriaId) {
        jdbcTemplate.update(DELETE_QUERY, criteriaId, exhibitionId);
    }

    private Criteria mapRowToCriteriaAtExhibition(ResultSet rs) throws SQLException {
        return Criteria.builder()
                .id(rs.getLong("criteria_id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .maxScore(rs.getByte("maxscore"))
                .build();
    }

}
