package fr.dawan.santeplus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.santeplus.dto.MedicamentDTO;
import fr.dawan.santeplus.entities.Medicament;
import fr.dawan.santeplus.service.MedicamentService;

@RestController
@RequestMapping("/api/medicaments")
public class MedicamentController {

    @Autowired
    private MedicamentService medicamentService;

    @GetMapping
    public List<MedicamentDTO> getAllMedicaments() {
        return medicamentService.getAllMedicaments();
    }

    @GetMapping("/{code}")
    public ResponseEntity<MedicamentDTO> getMedicamentByCode(@PathVariable String code) {
        MedicamentDTO medicament = medicamentService.getMedicamentByCode(code);
        return ResponseEntity.ok(medicament);
    }

    @PostMapping
    public ResponseEntity<MedicamentDTO> addMedicament(@RequestBody MedicamentDTO medicamentDTO) {
        MedicamentDTO savedMedicament = medicamentService.addMedicament(medicamentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicament);
    }

    @PutMapping("/{code}")
    public ResponseEntity<MedicamentDTO> updateMedicament(@PathVariable String code, @RequestBody MedicamentDTO medicamentDTO) {
        MedicamentDTO updatedMedicament = medicamentService.updateMedicament(code, medicamentDTO);
        return ResponseEntity.ok(updatedMedicament);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteMedicament(@PathVariable String code) {
        medicamentService.deleteMedicament(code);
        return ResponseEntity.noContent().build();
    }
}
