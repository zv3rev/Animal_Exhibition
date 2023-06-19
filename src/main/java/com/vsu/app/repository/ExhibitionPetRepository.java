package com.vsu.app.repository;

import com.vsu.app.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ExhibitionPetRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_QUERY = "INSERT INTO animal_at_exhibition (pet_id, exhibition_id) VALUES (?, ?) RETURNING id";
    private static final String DELETE_QUERY = "DELETE FROM animal_at_exhibition WHERE pet_id = ? AND exhibition_id = ?";

    public Long add(Long exhibitionId, Long petId) {
        return jdbcTemplate.queryForObject(INSERT_QUERY, Long.class, petId, exhibitionId);
    }

    public void delete(Long exhibitionId, Long petId) {
        jdbcTemplate.update(DELETE_QUERY, petId, exhibitionId);
    }
}
