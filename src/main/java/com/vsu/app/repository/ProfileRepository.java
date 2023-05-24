package com.vsu.app.repository;

import com.vsu.app.entity.Profile;
import com.vsu.app.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class ProfileRepository {
    public static final String INSERT_QUERY = "INSERT INTO profile (username, password, fio, email, phone, role) VALUES (?,?,?,?,?,?)";
    public static final String SELECT_BY_USERNAME_QUERY = "SELECT id, username, password, fio, email, phone, role FROM profile WHERE username = ?";
    public static final String SELECT_BY_ID_QUERY = "SELECT id, username, password, fio, email, phone, role FROM profile WHERE id = ?";
    public static final String SELECT_ALL_QUERY = "SELECT id, username, password, fio, email, phone, role FROM profile";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM profile WHERE id = ?";
    public static final String UPDATE_BY_ID_QUERY = "UPDATE profile SET password=?, fio=?, email=?, phone=?, role=? WHERE id=?;";
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
                    SELECT_BY_USERNAME_QUERY,
                    (rs, row) -> buildProfile(rs),
                    username);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }

        return profile;
    }

    public Profile getById(Long id){
        Profile profile;
        try {
            profile = jdbcTemplate.queryForObject(
                    SELECT_BY_ID_QUERY,
                    (rs, row) -> buildProfile(rs),
                    id);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }

        return profile;
    }

    public List<Profile> getAllUsers(){
        try{
            return jdbcTemplate.query(
                    SELECT_ALL_QUERY,
                    (rs,row) -> buildProfile(rs));
        }
        catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public boolean delete(Long id) {
        return jdbcTemplate.update(DELETE_BY_ID_QUERY, id) == 1;
    }

    public boolean update(Profile profile){
        return jdbcTemplate.update(
                UPDATE_BY_ID_QUERY,
                profile.getPassword(),
                profile.getFio(),
                profile.getEmail(),
                profile.getPhone(),
                profile.getRole().toString(),
                profile.getId()) == 1;
    }

    private static Profile buildProfile(ResultSet rs) throws SQLException {
        return Profile.builder()
                .id(rs.getLong(1))
                .username(rs.getString(2))
                .password(rs.getString(3))
                .fio(rs.getString(4))
                .email(rs.getString(5))
                .phone(rs.getLong(6))
                .role(Role.valueOf(rs.getString(7)))
                .build();
    }
}
