package com.epf.Persistance.ImplementationDAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.epf.Core.models.Plante;
import com.epf.Persistance.InterfaceDAO.DAOPlanteInterface;
import com.epf.Persistance.Exception.PlanteValidationException;
import java.util.Arrays;
import java.util.List;

@Repository
public class DAOPlanteImpl implements DAOPlanteInterface {
    private JdbcTemplate jdbcTemplate;
    private static final List<String> VALID_EFFECTS = Arrays.asList("normal", "slow low", "slow medium", "slow stop");

    public DAOPlanteImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }    private void validatePlant(Plante plante) {
        if (plante.getNom() == null || plante.getNom().trim().isEmpty()) {
            throw new PlanteValidationException("Le nom de la plante ne peut pas être vide");
        }

        if (plante.getPointDeVie() <= 0) {
            throw new PlanteValidationException("Les points de vie doivent être supérieurs à 0");
        }

        if (plante.getCout() <= 0) {
            throw new PlanteValidationException("Le coût doit être supérieur à 0");
        }

        if (plante.getDegatAttaque() < 0) {
            throw new PlanteValidationException("Les dégâts d'attaque ne peuvent pas être négatifs");
        }

        if (plante.getAttaqueParSeconde() < 0) {
            throw new PlanteValidationException("L'attaque par seconde ne peut pas être négative");
        }

        if (plante.getSoleilParSeconde() < 0) {
            throw new PlanteValidationException("La production de soleil par seconde ne peut pas être négative");
        }

        if (plante.getEffet() != null && !VALID_EFFECTS.contains(plante.getEffet())) {
            throw new PlanteValidationException(
                    "L'effet doit être l'une des valeurs suivantes : normal, slow low, slow medium, slow stop");
        }

        if (plante.getCheminImage() != null && plante.getCheminImage().trim().isEmpty()) {
            throw new PlanteValidationException("Le chemin d'image ne peut pas être une chaîne vide");
        }
    }

    public void addPlant(Plante plante) {
        validatePlant(plante);
        String sql = "INSERT INTO plante (nom,point_de_vie,cout,degat_attaque,attaque_par_seconde,soleil_par_seconde,effet,chemin_image) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, plante.getNom(),
                plante.getPointDeVie(), plante.getCout(), plante.getDegatAttaque(),
                plante.getAttaqueParSeconde(), plante.getSoleilParSeconde(),
                plante.getEffet(), plante.getCheminImage());
    }

    public List<Plante> getAllPlants() {
        String sql = "SELECT * FROM plante";
        RowMapper<Plante> rowMapper = (rs, rowNum) -> new Plante(
                rs.getInt("id_plante"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getInt("cout"),
                rs.getInt("degat_attaque"),
                rs.getDouble("attaque_par_seconde"),
                rs.getDouble("soleil_par_seconde"),
                rs.getString("effet"),
                rs.getString("chemin_image"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void updatePlant(Plante plante) {
        validatePlant(plante);
        String sql = "UPDATE plante SET nom = ?, point_de_vie = ?, cout = ?, degat_attaque = ?, " +
                "attaque_par_seconde = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? " +
                "WHERE id_plante = ?";
        jdbcTemplate.update(sql,
                plante.getNom(),
                plante.getPointDeVie(),
                plante.getCout(),
                plante.getDegatAttaque(),
                plante.getAttaqueParSeconde(),
                plante.getSoleilParSeconde(),
                plante.getEffet(),
                plante.getCheminImage(),
                plante.getId_plante());
    }

    public void deletePlante(Plante plante) {
        String sql = "DELETE FROM plante WHERE id_plante = ?";
        jdbcTemplate.update(sql, plante.getId_plante());
    }
}
