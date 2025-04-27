package com.epf.Persistance.ImplementationDAO;

import org.springframework.jdbc.core.JdbcTemplate;

import com.epf.Core.models.Map;
import com.epf.Persistance.InterfaceDAO.DAOMapInterface;
import com.epf.Persistance.Exception.MapValidationException;

import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DAOMapImpl implements DAOMapInterface {
    private JdbcTemplate jdbcTemplate;

    public DAOMapImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void validateMap(Map Map) {
        if (Map.getLigne() <= 0) {
            throw new MapValidationException("Le nombre de lignes doit être supérieur à 0");
        }

        if (Map.getColonne() <= 0) {
            throw new MapValidationException("Le nombre de colonnes doit être supérieur à 0");
        }

        if (Map.getCheminImage() != null && Map.getCheminImage().trim().isEmpty()) {
            throw new MapValidationException("Le chemin d'image ne peut pas être une chaîne vide");
        }
    }
    
    public void addMap(Map Map) {
        validateMap(Map);
        String sql = "INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, 
            Map.getLigne(),
            Map.getColonne(),
            Map.getCheminImage());
    }

    public List<Map> getAllMaps() {
        String sql = "SELECT * FROM map";
        RowMapper<Map> rowMapper = (rs, rowNum) -> new Map(
            rs.getInt("id_map"),
            rs.getInt("ligne"),
            rs.getInt("colonne"),
            rs.getString("chemin_image"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void updateMap(Map Map) {
        validateMap(Map);
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql,
            Map.getLigne(),
            Map.getColonne(),
            Map.getCheminImage(),
            Map.getIdMap());
    }

    public void deleteMap(Map Map) {
        String sql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update(sql, Map.getIdMap());
    }
}
