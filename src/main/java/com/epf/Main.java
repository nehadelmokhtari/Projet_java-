package com.epf;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.epf.Core.models.Map;
import com.epf.Core.models.Plante;
import com.epf.Core.models.Zombie;
import com.epf.Persistance.Config.ConfigBDD;
import com.epf.Persistance.ImplementationDAO.DAOMapImpl;
import com.epf.Persistance.ImplementationDAO.DAOPlanteImpl;
import com.epf.Persistance.ImplementationDAO.DAOZombieImpl;

@ComponentScan(basePackages = "com.epf")
public class Main {

    private static void displayObjectList(String title, List<?> objects) {
        System.out.println("\n=== " + title + " ===");
        for (Object object : objects) {
            System.out.println(object);
            System.out.println("--------------------");
        }
    }

    private static Plante displayPlanteDetails(Plante plante) {
        System.out.println("ID: " + plante.getId_plante());
        System.out.println("Nom: " + plante.getNom());
        System.out.println("PV: " + plante.getPointDeVie());
        System.out.println("Coût: " + plante.getCout());
        System.out.println("Dégâts: " + plante.getDegatAttaque());
        System.out.println("Attaque/s: " + plante.getAttaqueParSeconde());
        System.out.println("Soleil/s: " + plante.getSoleilParSeconde());
        System.out.println("Effet: " + plante.getEffet());
        return plante;
    }

    private static Zombie displayZombieDetails(Zombie zombie) {
        System.out.println("ID: " + zombie.getId_zombie());
        System.out.println("Nom: " + zombie.getNom());
        System.out.println("PV: " + zombie.getPoint_de_vie());
        System.out.println("Attaque/s: " + zombie.getAttaque_par_seconde());
        System.out.println("Dégâts: " + zombie.getDegat_attaque());
        System.out.println("Vitesse: " + zombie.getVitesse_de_deplacement());
        System.out.println("id_map: " + zombie.getId_map());
        return zombie;
    }

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBDD.class)) {
            DAOPlanteImpl daoPlant = context.getBean(DAOPlanteImpl.class);
            DAOZombieImpl daoZombie = context.getBean(DAOZombieImpl.class);
            DAOMapImpl daoMap = context.getBean(DAOMapImpl.class);
            
            List<Plante> plantes = daoPlant.getAllPlants();
            List<Map> maps = daoMap.getAllMaps();
            List<Zombie> zombies = daoZombie.getAllZombies();
            List<Zombie> zombiesFromMap = daoZombie.getZombiesFromMap(maps.get(0));

            System.out.println("\033c"); 

            displayObjectList("PLANTES", plantes);
            displayObjectList("ZOMBIES", zombies);
            displayObjectList("ZOMBIES DE LA MAP", zombiesFromMap);
            displayObjectList("MAPS", maps);
        }
    }
}
