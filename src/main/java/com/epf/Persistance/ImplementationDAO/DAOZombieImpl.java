package com.epf.Persistance.ImplementationDAO;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.epf.Core.models.Map;
import com.epf.Core.models.Zombie;
import com.epf.Persistance.InterfaceDAO.DAOZombieInterface;
import com.epf.Persistance.Exception.ZombieValidationException;

@Repository
public class DAOZombieImpl implements DAOZombieInterface {

    private JdbcTemplate jdbcTemplate;

    public DAOZombieImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void validateZombie(Zombie zombie) {
        if (zombie.getNom() == null || zombie.getNom().trim().isEmpty()) {
            throw new ZombieValidationException("Le nom du zombie ne peut pas être vide");
        }

        if (zombie.getPoint_de_vie() <= 0) {
            throw new ZombieValidationException("Les points de vie doivent être supérieurs à 0");
        }

        if (zombie.getAttaque_par_seconde() < 0) {
            throw new ZombieValidationException("L'attaque par seconde ne peut pas être négative");
        }

        if (zombie.getDegat_attaque() < 0) {
            throw new ZombieValidationException("Les dégâts d'attaque ne peuvent pas être négatifs");
        }

        if (zombie.getVitesse_de_deplacement() < 0) {
            throw new ZombieValidationException("La vitesse de déplacement ne peut pas être négative");
        }

        if (zombie.getChemin_image() != null && zombie.getChemin_image().trim().isEmpty()) {
            throw new ZombieValidationException("Le chemin d'image ne peut pas être une chaîne vide");
        }
    }
    
    public void addZombie(Zombie zombie) {
        validateZombie(zombie);
        String sql = "INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, zombie.getNom(),
                zombie.getPoint_de_vie(), 
                zombie.getAttaque_par_seconde(),
                zombie.getDegat_attaque(),
                zombie.getVitesse_de_deplacement(),
                zombie.getChemin_image());
    }

    public List<Zombie> getAllZombies() {
        String sql = "SELECT * FROM zombie";
        RowMapper<Zombie> rowMapper = (rs, rowNum) -> new Zombie(
                rs.getInt("id_zombie"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getInt("attaque_par_seconde"),
                rs.getInt("degat_attaque"),
                rs.getInt("vitesse_de_deplacement"),
                rs.getString("chemin_image"),
                rs.getInt("id_map"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Zombie> getZombiesFromMap(Map map) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        RowMapper<Zombie> rowMapper = (rs, rowNum) -> new Zombie(
                rs.getInt("id_zombie"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getInt("attaque_par_seconde"),
                rs.getInt("degat_attaque"),
                rs.getInt("vitesse_de_deplacement"),
                rs.getString("chemin_image"),
                rs.getInt("id_map"));
        return jdbcTemplate.query(sql, rowMapper, map.getIdMap());
    }

    public void updateZombie(Zombie zombie) {
        validateZombie(zombie);
        String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, " +
                "vitesse_de_deplacement = ?, chemin_image = ? WHERE id_zombie = ?";
        jdbcTemplate.update(sql,
                zombie.getNom(),
                zombie.getPoint_de_vie(),
                zombie.getAttaque_par_seconde(),
                zombie.getDegat_attaque(),
                zombie.getVitesse_de_deplacement(),
                zombie.getChemin_image(),
                zombie.getId_zombie());
    }

    public void deleteZombie(Zombie zombie) {
        String sql = "DELETE FROM zombie WHERE id_zombie = ?";
        jdbcTemplate.update(sql, zombie.getId_zombie());
    }

    public void deleteZombiesFromMap(Map Map) {
        String sql = "DELETE FROM zombie WHERE id_map = ?";
        jdbcTemplate.update(sql, Map.getIdMap());
    }
}
