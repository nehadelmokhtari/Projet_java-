package com.epf.Persistance.InterfaceDAO;

import java.util.List;

import com.epf.Core.models.Plante;

public interface DAOPlanteInterface {
    void addPlant(Plante plante);
    List<Plante> getAllPlants();
    void updatePlant(Plante plante);
    void deletePlante(Plante plante);
}
