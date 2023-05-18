package com.vsu.app.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SpeciesRepository {
    public static final String INSERT_QUERY = "INSERT INTO species (name) VALUES (?)";
    public static final String DELETE_QUERY = "DELETE FROM species WHERE id = ?;";
    private final JdbcTemplate jdbcTemplate;

    public boolean create(String name) {
        return jdbcTemplate.update(
                INSERT_QUERY,
                name) == 1;
    }

    public boolean delete(Long id) {
        return jdbcTemplate.update(
                DELETE_QUERY,
                id) == 1;
    }
}
