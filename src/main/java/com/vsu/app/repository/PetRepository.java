package com.vsu.app.repository;

import com.vsu.app.entity.Pet;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PetRepository {
    public static final String SELECT_BY_OWNER_ID_QUERY = "SELECT id, nickname, birthday, breed_id, owner_id FROM pet WHERE owner_id = ?";
    private JdbcTemplate jdbcTemplate;
    public List<Pet> getByOwnerId(Long userId) {
        return jdbcTemplate.query(
                SELECT_BY_OWNER_ID_QUERY,
                (rs,row) -> Pet.builder()
                        .id(rs.getLong(1))
                        .nickname(rs.getString(2))
                        .birthday(rs.getDate(3))
                        .breed_id(rs.getLong(4))
                        .owner_id(rs.getLong(5)).build(),
                userId);
    }
}
