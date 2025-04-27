package com.epf.Core.ImplementationService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.Core.InterfaceService.ServicePlanteInterface;
import com.epf.Core.models.Plante;
import com.epf.Persistance.ImplementationDAO.DAOPlanteImpl;

@Service
public class ServicePlanteImpl implements ServicePlanteInterface {
    private DAOPlanteImpl DAOImpl;

    public ServicePlanteImpl(DAOPlanteImpl DAOPlantImpl) {
        this.DAOImpl = DAOPlantImpl;
    }

    public void addPlant(Plante plante) {
        DAOImpl.addPlant(plante);
    }

    public List<Plante> getAllPlants() {
        return DAOImpl.getAllPlants();
    }

    public void updatePlant(Plante plante) {
        DAOImpl.updatePlant(plante);
    }

    public void deletePlant(Plante plante) {
        DAOImpl.deletePlante(plante);
    }
}
