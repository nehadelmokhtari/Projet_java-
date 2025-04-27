package com.epf.Persistance.InterfaceDAO;

import java.util.List;

import com.epf.Core.models.Map;
import com.epf.Core.models.Zombie;

public interface DAOZombieInterface {
    void addZombie(Zombie zombie);
    List<Zombie> getAllZombies();
    List<Zombie> getZombiesFromMap(Map Map);
    void updateZombie(Zombie zombie);
    void deleteZombie(Zombie zombie);
    void deleteZombiesFromMap(Map Map);
}
