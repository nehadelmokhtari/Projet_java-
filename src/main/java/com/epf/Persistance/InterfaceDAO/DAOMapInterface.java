package com.epf.Persistance.InterfaceDAO;

import java.util.List;

import com.epf.Core.models.Map;

public interface DAOMapInterface {
    void addMap(Map Map);
    List<Map> getAllMaps();
    void updateMap(Map Map);
    void deleteMap(Map Map);
}
