package com.vsu.app.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SpeciesRepository {
    public static final String INSERT_QUERY = "INSERT INTO species (name) VALUES (?)";
    private final JdbcTemplate jdbcTemplate;

    public boolean create(String name) {
        return jdbcTemplate.update(
                INSERT_QUERY,
                name) == 1;
    }
}
