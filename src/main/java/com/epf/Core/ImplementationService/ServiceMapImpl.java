package com.epf.Core.ImplementationService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.Core.InterfaceService.ServiceMapInterface;
import com.epf.Core.models.Map;
import com.epf.Persistance.ImplementationDAO.DAOMapImpl;

@Service
public class ServiceMapImpl implements ServiceMapInterface {

    private DAOMapImpl DAOImpl;

    public ServiceMapImpl(DAOMapImpl DAOMapImpl){
        this.DAOImpl = DAOMapImpl;
    }

    public void addMap(Map Map){
        DAOImpl.addMap(Map);
    }

    public List<Map> getMaps(){
        return DAOImpl.getAllMaps();
    }

    public void updateMap(Map Map){
        DAOImpl.updateMap(Map);
    }
    
    public void deleteMap(Map Map){
        DAOImpl.deleteMap(Map);
    }
}
