package com.vsu.app.repository;

import com.vsu.app.entity.Breed;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class BreedRepository {
    public static final String INSERT_QUERY = "INSERT INTO breed (name, species_id) VALUES (?,?)";
    public static final String SELECT_BY_ID_QUERY = "SELECT id, name, species_id FROM breed WHERE id = ?";
    public static final String DELETE_QUERY = "DELETE FROM breed WHERE id = ?";
    public static final String SELECT_BY_SPECIES_QUERY = "SELECT id, name, species_id FROM breed WHERE species_id = ?";
    private JdbcTemplate jdbcTemplate;

    //TODO: сделать обработку несуществующего внешнего ключа
    //TODO: сделать обработку неуникальной строки
    public boolean create(Long speciesId, String breedName) {
        return jdbcTemplate.update(INSERT_QUERY,breedName,speciesId) == 1;
    }

    public Breed getById(Long breedId) {
        return jdbcTemplate.queryForObject(
                SELECT_BY_ID_QUERY,
                (rs,row) -> buildBreed(rs),
                breedId);
    }

    private static Breed buildBreed(ResultSet rs) throws SQLException {
        return Breed.builder()
                .id(rs.getLong(1))
                .name(rs.getString(2))
                .species_id(rs.getLong(3))
                .build();
    }

    public boolean delete(Long breedId) {
        return jdbcTemplate.update(
                DELETE_QUERY,
                breedId) == 1;
    }

    public List<Breed> getBySpecies(Long speciesId) {
        return jdbcTemplate.query(
                SELECT_BY_SPECIES_QUERY,
                (rs, row) -> buildBreed(rs),
                speciesId);
    }
}
