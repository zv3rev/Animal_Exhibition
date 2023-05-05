package com.vsu.app.repository;

import com.vsu.app.entity.Species;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpeciesRepository {
    public static final String SELECT_BY_ID_QUERY = "SELECT id, name FROM species WHERE id =?";
    private final JdbcTemplate jdbcTemplate;

    public SpeciesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Species getById(long id) {
        return jdbcTemplate.queryForObject(
                SELECT_BY_ID_QUERY,
                (rs, rowNum) -> Species.builder().id(rs.getLong(1)).name(rs.getString(2)).build(),
                id);
    }
}
