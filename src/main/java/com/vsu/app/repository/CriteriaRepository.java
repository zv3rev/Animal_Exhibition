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
}
