package com.epf.API.Controller;

import com.epf.API.DTO.PlanteDTO;
import com.epf.Core.InterfaceService.ServicePlanteInterface;
import com.epf.Core.models.Plante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plantes")
@CrossOrigin(origins = "http://localhost:5173")
public class PlanteController {   
    private final ServicePlanteInterface servicePlant;

    public PlanteController(ServicePlanteInterface servicePlant) {
        this.servicePlant = servicePlant;
    }

    @GetMapping
    public ResponseEntity<List<PlanteDTO>> getAllPlants() {
        List<Plante> plantes = servicePlant.getAllPlants();
        List<PlanteDTO> plantDTOs = plantes.stream()
            .map(p -> new PlanteDTO(
                p.getId_plante(),
                p.getNom(),
                p.getPointDeVie(),
                p.getCout(),
                p.getDegatAttaque(),
                p.getAttaqueParSeconde(),
                p.getSoleilParSeconde(),
                p.getEffet(),
                p.getCheminImage()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(plantDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanteDTO> getPlantById(@PathVariable("id") int id) {
        Plante plante = servicePlant.getAllPlants().stream()
            .filter(p -> p.getId_plante() == id)
            .findFirst()
            .orElse(null);
        
        if (plante == null) {
            return ResponseEntity.notFound().build();
        }

        PlanteDTO plantDTO = new PlanteDTO(
            plante.getId_plante(),
            plante.getNom(),
            plante.getPointDeVie(),
            plante.getCout(),
            plante.getDegatAttaque(),
            plante.getAttaqueParSeconde(),
            plante.getSoleilParSeconde(),
            plante.getEffet(),
            plante.getCheminImage()
        );
        
        return ResponseEntity.ok(plantDTO);
    }

    @PostMapping
    public ResponseEntity<PlanteDTO> createPlant(@RequestBody PlanteDTO plantDTO) {
        Plante plante = new Plante(
            plantDTO.getId(),
            plantDTO.getNom(),
            plantDTO.getPointDeVie(),
            plantDTO.getCout(),
            plantDTO.getDegatAttaque(),
            plantDTO.getAttaqueParSeconde(),
            plantDTO.getSoleilParSeconde(),
            plantDTO.getEffet(),
            plantDTO.getCheminImage()
        );
        
        servicePlant.addPlant(plante);
        return ResponseEntity.ok(plantDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanteDTO> updatePlant(@PathVariable("id") int id, @RequestBody PlanteDTO plantDTO) {
        Plante plante = new Plante(
            plantDTO.getId(),
            plantDTO.getNom(),
            plantDTO.getPointDeVie(),
            plantDTO.getCout(),
            plantDTO.getDegatAttaque(),
            plantDTO.getAttaqueParSeconde(),
            plantDTO.getSoleilParSeconde(),
            plantDTO.getEffet(),
            plantDTO.getCheminImage()
        );
        plante.setId_plante(id);
        
        servicePlant.updatePlant(plante);
        return ResponseEntity.ok(plantDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable("id") int id) {
        Plante plante = servicePlant.getAllPlants().stream()
            .filter(p -> p.getId_plante() == id)
            .findFirst()
            .orElse(null);
        
        if (plante == null) {
            return ResponseEntity.notFound().build();
        }
        
        servicePlant.deletePlant(plante);
        return ResponseEntity.ok().build();
    }
}
