package com.vsu.app.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SpeciesRepository {
    public static final String INSERT_QUERY = "INSERT INTO species (name) VALUES (?)";
    public static final String DELETE_QUERY = "DELETE FROM species WHERE id = ?;";
    public static final String SELECT_ALL_QUERY = "SELECT name FROM species;";
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

    public List<String> getAll() {
        return jdbcTemplate.queryForList(SELECT_ALL_QUERY, String.class);
    }
}
