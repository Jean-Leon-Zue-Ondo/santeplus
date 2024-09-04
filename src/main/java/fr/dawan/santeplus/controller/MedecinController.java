package fr.dawan.santeplus.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.santeplus.dto.MedecinDTO;
import fr.dawan.santeplus.service.MedecinService;

@RestController
@RequestMapping("/api/medecins")
public class MedecinController {
    
    @Autowired
    private MedecinService medecinService;

    @GetMapping
    public Page<MedecinDTO> getAllMedecins(@RequestParam(required = false) String nomMED,
                                           @RequestParam(required = false) String matricule,
                                           Pageable pageable) {
        return medecinService.getAllMedecins(nomMED, matricule, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedecinDTO> getMedecinById(@PathVariable Long id) {
        MedecinDTO medecin = medecinService.getMedecinById(id);
        return ResponseEntity.ok(medecin);
    }

    @PostMapping
    public ResponseEntity<MedecinDTO> addMedecin(@RequestBody MedecinDTO medecinDTO) {
        MedecinDTO savedMedecin = medecinService.addMedecin(medecinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedecin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedecinDTO> updateMedecin(@PathVariable Long id, @RequestBody MedecinDTO medecinDTO) {
        MedecinDTO updatedMedecin = medecinService.updateMedecin(id, medecinDTO);
        return ResponseEntity.ok(updatedMedecin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return ResponseEntity.noContent().build();
    }
}
