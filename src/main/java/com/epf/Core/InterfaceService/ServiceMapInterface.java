package com.epf.Core.InterfaceService;

import java.util.List;

import com.epf.Core.models.Map;

public interface ServiceMapInterface {
    void addMap(Map Map);
    List<Map> getMaps();
    void updateMap(Map Map);
    void deleteMap(Map Map);
}
