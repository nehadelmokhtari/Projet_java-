package com.epf.API.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.epf.API.DTO.MapDTO;
import com.epf.Core.InterfaceService.ServiceMapInterface;
import com.epf.Core.InterfaceService.ServiceZombieInterface;
import com.epf.Core.models.Map;

@RestController
@RequestMapping("/maps")
@CrossOrigin(origins = "http://localhost:5173")
public class MapController {
    private final ServiceMapInterface serviceMap;
    private final ServiceZombieInterface serviceZombie;

    public MapController(ServiceMapInterface serviceMap, ServiceZombieInterface serviceZombie) {
        this.serviceMap = serviceMap;
        this.serviceZombie = serviceZombie;
    }

    @GetMapping
    public ResponseEntity<List<MapDTO>> getAllMaps() {
        List<Map> maps = serviceMap.getMaps();
        List<MapDTO> mapDTOs = maps.stream()
            .map(m -> new MapDTO(
                m.getIdMap(),
                m.getLigne(),
                m.getColonne(),
                m.getCheminImage()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(mapDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapDTO> getMapById(@PathVariable("id") int id) {
        Map map = serviceMap.getMaps().stream()
            .filter(m -> m.getIdMap() == id)
            .findFirst()
            .orElse(null);
        
        if (map == null) {
            return ResponseEntity.notFound().build();
        }

        MapDTO mapDTO = new MapDTO(
            map.getIdMap(),
            map.getLigne(),
            map.getColonne(),
            map.getCheminImage()
        );
        
        return ResponseEntity.ok(mapDTO);
    }

    @PostMapping
    public ResponseEntity<MapDTO> createMap(@RequestBody MapDTO mapDTO) {
        Map map = new Map(
            mapDTO.getId_map(),
            mapDTO.getLigne(),
            mapDTO.getColonne(),
            mapDTO.getChemin_image()
        );
        serviceMap.addMap(map);
        return ResponseEntity.ok(mapDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MapDTO> updateMap(@PathVariable("id") int id, @RequestBody MapDTO mapDTO) {
        Map map = new Map(
            id,
            mapDTO.getLigne(),
            mapDTO.getColonne(),
            mapDTO.getChemin_image()
        );
        serviceMap.updateMap(map);
        return ResponseEntity.ok(mapDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMap(@PathVariable("id") int id) {
        Map map = serviceMap.getMaps().stream()
            .filter(m -> m.getIdMap() == id)
            .findFirst()
            .orElse(null);
            
        if (map == null) {
            return ResponseEntity.notFound().build();
        }
        
        serviceZombie.deleteZombiesFromMap(map);
        serviceMap.deleteMap(map);
        return ResponseEntity.ok().build();
    }
}
