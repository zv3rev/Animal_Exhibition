package com.vsu.app.repository;

import com.vsu.app.entity.Profile;
import com.vsu.app.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class JudgeRepository {
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_BY_EXHIBITION_ID_QUERY = "SELECT jae.id, jae.user_id, jae.exhibition_id, p.id p_id, p.username p_username, p.password p_password, p.fio p_fio, p.email p_email, p.phone p_phone, p.role p_role FROM judge_at_exhibition jae JOIN profile p ON jae.user_id = p.id WHERE exhibition_id = ?";

    private static final String INSERT_QUERY = "INSERT INTO judge_at_exhibition (user_id, exhibition_id) VALUES (?, ?) RETURNING id";
    private static final String DELETE_QUERY = "DELETE FROM judge_at_exhibition WHERE user_id = ? AND exhibition_id = ?";

    public List<Profile> getAll(Long exhibitionId) {
        return jdbcTemplate.query(SELECT_ALL_BY_EXHIBITION_ID_QUERY, (rs,row) -> buildProfile(rs), exhibitionId);
    }

    public Long add(Long exhibitionId, Long judgeId) {
        return jdbcTemplate.queryForObject(INSERT_QUERY, Long.class, judgeId, exhibitionId);
    }

    public void delete(Long exhibitionId, Long judgeId) {
        jdbcTemplate.update(DELETE_QUERY, judgeId, exhibitionId);
    }

    private static Profile buildProfile(ResultSet rs) throws SQLException {
        return Profile.builder()
                .id(rs.getLong("p_id"))
                .username(rs.getString("p_username"))
                .password(rs.getString("p_password"))
                .fio(rs.getString("p_fio"))
                .email(rs.getString("p_email"))
                .phone(rs.getLong("p_phone"))
                .role(Role.valueOf(rs.getString("p_role")))
                .build();
    }
}
