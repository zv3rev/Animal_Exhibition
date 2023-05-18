package com.vsu.app.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BreedRepository {
    public static final String INSERT_QUERY = "INSERT INTO breed (name, species_id) VALUES (?,?)";
    private JdbcTemplate jdbcTemplate;

    //TODO: сделать обработку несуществующего внешнего ключа
    //TODO: сделать обработку неуникальной строки
    public boolean create(Long speciesId, String breedName) {
        return jdbcTemplate.update(INSERT_QUERY,breedName,speciesId) == 1;
    }
}
