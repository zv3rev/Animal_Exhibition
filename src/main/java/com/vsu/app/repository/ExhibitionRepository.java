package com.vsu.app.repository;

import com.vsu.app.entity.Exhibition;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class ExhibitionRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_QUERY = "SELECT id, name, description, start_date, end_date, species_id, enter_price FROM Exhibition";
    private static final String SELECT_BY_ID_QUERY = SELECT_ALL_QUERY + " WHERE id = ?";
    private static final String SELECT_BY_SPECIES_ID_QUERY = SELECT_ALL_QUERY + " WHERE species_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO Exhibition (name, description, start_date, end_date, species_id, enter_price) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
    private static final String UPDATE_QUERY = "UPDATE Exhibition SET name = ?, description = ?, start_date = ?, end_date = ?, species_id = ?, enter_price = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Exhibition WHERE id = ?";

    public List<Exhibition> getAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, (rs,rowNum) -> mapRowToExhibition(rs));
    }

    public Exhibition getById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, (rs,rowNum) -> mapRowToExhibition(rs), id);
    }

    public Long create(Exhibition exhibition) {
        return jdbcTemplate.queryForObject(INSERT_QUERY, Long.class, exhibition.getName(), exhibition.getDescription(), exhibition.getStart(), exhibition.getEnd(), exhibition.getSpeciesId(), exhibition.getEnterPrice());
    }

    public void update(Long exhibitionId, Exhibition exhibition) {
        jdbcTemplate.update(UPDATE_QUERY, exhibition.getName(), exhibition.getDescription(), exhibition.getStart(), exhibition.getEnd(), exhibition.getSpeciesId(), exhibition.getEnterPrice(), exhibitionId);
    }

    public void delete(Long id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    public List<Exhibition> getBySpeciesId(Long speciesId) {
        return jdbcTemplate.query(SELECT_BY_SPECIES_ID_QUERY, (rs,rowNum) -> mapRowToExhibition(rs), speciesId);
    }

    private Exhibition mapRowToExhibition(ResultSet rs) throws SQLException {
        return Exhibition.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .start(rs.getDate("start_date"))
                .end(rs.getDate("end_date"))
                .speciesId(rs.getLong("species_id"))
                .enterPrice(rs.getDouble("enter_price"))
                .build();
    }


}