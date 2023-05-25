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
public class PetRepository {
    public static final String SELECT_BY_OWNER_ID_QUERY = "SELECT id, nickname, birthday, breed_id, owner_id FROM pet WHERE owner_id = ?";
    public static final String INSERT_QUERY = "INSERT INTO pet (nickname, birthday,breed_id,owner_id) VALUES (?,?,?,?) RETURNING id;";
    public static final String SELECT_BY_ID_QUERY = "SELECT id, nickname, birthday, breed_id, owner_id FROM pet WHERE id = ?";
    public static final String DELETE_QUERY = "DELETE FROM pet WHERE id = ?";
    private JdbcTemplate jdbcTemplate;

    public Long create(Pet pet) {
        return jdbcTemplate.queryForObject(
                INSERT_QUERY,
                Long.class,
                pet.getNickname(),
                pet.getBirthday(),
                pet.getBreed_id(),
                pet.getOwner_id());
    }

    public List<Pet> getByOwnerId(Long userId) {
        return jdbcTemplate.query(
                SELECT_BY_OWNER_ID_QUERY,
                (rs,row) -> buildPet(rs),
                userId);
    }

    private static Pet buildPet(ResultSet rs) throws SQLException {
        return Pet.builder()
                .id(rs.getLong(1))
                .nickname(rs.getString(2))
                .birthday(rs.getDate(3))
                .breed_id(rs.getLong(4))
                .owner_id(rs.getLong(5)).build();
    }


    public Pet getById(Long petId) {
        return jdbcTemplate.queryForObject(
                SELECT_BY_ID_QUERY,
                (rs,row) -> buildPet(rs),
                petId);
    }

    //TODO: добавить удаление связанных строк из animal_at_exhibition и animal_score
    public boolean delete(Long petId) {
        return jdbcTemplate.update(
                DELETE_QUERY,
                petId) == 1;
    }
}
