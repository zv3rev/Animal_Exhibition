package com.vsu.app.repository;

import com.vsu.app.entity.Criteria;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CriteriaRepository {
    public static final String INSERT_QUERY = "INSERT INTO criteria (name, description, maxscore) VALUES(?, ?, ?);";
    private JdbcTemplate jdbcTemplate;
    public boolean create(Criteria criteria){
        return jdbcTemplate.update(
                INSERT_QUERY,
                criteria.getName(),
                criteria.getDescription(),
                criteria.getMaxScore()) ==1 ;
    }
}
