package com.epf.Core.InterfaceService;

import java.util.List;

import com.epf.Core.models.Plante;

public interface ServicePlanteInterface {
    void addPlant(Plante plante);
    List<Plante> getAllPlants();
    void updatePlant(Plante plante);
    void deletePlant(Plante plante);
}
