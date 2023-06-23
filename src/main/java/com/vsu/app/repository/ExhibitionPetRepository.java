package com.vsu.app.repository;

import com.vsu.app.entity.Pet;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class ExhibitionPetRepository {

    private final JdbcTemplate jdbcTemplate;


    private static final String SELECT_BY_EXHIBITION_ID_QUERY = "SELECT aae.id, aae.pet_id, aae.exhibition_id, p.id pet_id, p.owner_id pet_ownerId, p.breed_id pet_breedId, p.nickname pet_nickname, p.birthday pet_birthday FROM animal_at_exhibition aae JOIN pet p ON aae.pet_id = p.id WHERE aae.exhibition_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO animal_at_exhibition (pet_id, exhibition_id) VALUES (?, ?) RETURNING id";
    private static final String DELETE_QUERY = "DELETE FROM animal_at_exhibition WHERE pet_id = ? AND exhibition_id = ?";

    public List<Pet> getAll(Long exhibitionId){
        return jdbcTemplate.query(SELECT_BY_EXHIBITION_ID_QUERY,(rs, rowNum) -> mapRowToPet(rs) , exhibitionId);
    }

    public Long add(Long exhibitionId, Long petId) {
        return jdbcTemplate.queryForObject(INSERT_QUERY, Long.class, petId, exhibitionId);
    }

    public void delete(Long exhibitionId, Long petId) {
        jdbcTemplate.update(DELETE_QUERY, petId, exhibitionId);
    }

    private Pet mapRowToPet(ResultSet rs) throws SQLException {
        return Pet.builder()
                .id(rs.getLong("pet_id"))
                .owner_id(rs.getLong("pet_ownerId"))
                .breed_id(rs.getLong("pet_breedId"))
                .nickname(rs.getString("pet_nickname"))
                .birthday(rs.getDate("pet_birthday"))
                .build();

    }
}
