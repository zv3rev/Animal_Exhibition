package com.vsu.app.repository;

import com.vsu.app.entity.Profile;
import com.vsu.app.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class ProfileRepository {
    public static final String INSERT_QUERY = "INSERT INTO profile (username, password, fio, email, phone, role) VALUES (?,?,?,?,?,?)";
    public static final String SELECT_BY_USERNAME = "SELECT id, username, password, fio, email, phone, role FROM profile WHERE username = ?";
    private JdbcTemplate jdbcTemplate;
    public boolean create(Profile profile) {
        return jdbcTemplate.update(
                INSERT_QUERY,
                profile.getUsername(),
                profile.getPassword(),
                profile.getFio(),
                profile.getEmail(),
                profile.getPhone(),
                profile.getRole().toString()) == 1;
    }

    public Profile getByUsername(String username){
        Profile profile;
        try {
            profile = jdbcTemplate.queryForObject(
                    SELECT_BY_USERNAME,
                    (rs, row) -> Profile.builder()
                            .id(rs.getLong(1))
                            .username(rs.getString(2))
                            .password(rs.getString(3))
                            .fio(rs.getString(4))
                            .email(rs.getString(5))
                            .phone(rs.getLong(6))
                            .role(Role.valueOf(rs.getString(7)))
                            .build(),
                    username);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }

        return profile;
    }
}
