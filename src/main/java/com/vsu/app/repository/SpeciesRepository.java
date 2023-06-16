package com.vsu.app.repository;

import com.vsu.app.entity.Species;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class SpeciesRepository {
    public static final String INSERT_QUERY = "INSERT INTO species (name) VALUES (?)";
    public static final String DELETE_QUERY = "DELETE FROM species WHERE id = ?;";
    public static final String SELECT_ALL_QUERY = "SELECT id, name FROM species;";
    public static final String SELECT_BY_ID_QUERY = "SELECT id, name FROM species WHERE id = ?";
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

    public List<Species> getAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY,
                (rs,row) -> buildSpecies(rs));
    }

    private static Species buildSpecies(ResultSet rs) throws SQLException {
        return Species.builder()
                .id(rs.getLong(1))
                .name(rs.getString(2))
                .build();
    }

    public Species get(Long id) {
        return jdbcTemplate.queryForObject(
                SELECT_BY_ID_QUERY,
                (rs,row)->buildSpecies(rs),
                id);
    }
}
