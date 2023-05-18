package com.vsu.app.repository;

import com.vsu.app.entity.Criteria;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CriteriaRepository {
    public static final String INSERT_QUERY = "INSERT INTO criteria (name, description, maxscore) VALUES(?, ?, ?);";
    public static final String DELETE_QUERY = "DELETE FROM criteria WHERE id=?";
    public static final String UPDATE_QUERY = "UPDATE public.criteria SET name=?, description=?, maxscore=? WHERE id=?;";
    public static final String SELECT_QUERY = "SELECT id, name, description, maxScore FROM criteria WHERE id = ?;";
    private JdbcTemplate jdbcTemplate;
    public boolean create(Criteria criteria){
        return jdbcTemplate.update(
                INSERT_QUERY,
                criteria.getName(),
                criteria.getDescription(),
                criteria.getMaxScore()) ==1 ;
    }

    public boolean delete(Long criteriaId) {
        return jdbcTemplate.update(DELETE_QUERY,criteriaId)==1;
    }

    public boolean update(Criteria criteria) {
        return jdbcTemplate.update(
                UPDATE_QUERY,
                criteria.getName(),
                criteria.getDescription(),
                criteria.getMaxScore(),
                criteria.getId())==1;
    }

    public Criteria get(Long criteriaId) {
        return jdbcTemplate.queryForObject(
                SELECT_QUERY,
                (rs,row) -> Criteria.builder()
                        .id(rs.getLong(1))
                        .name(rs.getString(2))
                        .description(rs.getString(3))
                        .maxScore(rs.getByte(4))
                        .build(),
                criteriaId);
    }
}
