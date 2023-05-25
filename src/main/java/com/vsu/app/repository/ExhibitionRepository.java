package com.vsu.app.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ExhibitionRepository {
    private JdbcTemplate jdbcTemplate;
}
