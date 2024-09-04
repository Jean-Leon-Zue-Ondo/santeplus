package fr.dawan.santeplus.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.santeplus.dto.ConsultationDTO;
import fr.dawan.santeplus.dto.PrescriptionDTO;
import fr.dawan.santeplus.service.ConsultationService;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {
    
    @Autowired
    private ConsultationService consultationService;

    
    /*
     * traite une requête HTTP GET pour obtenir une page de consultations d'un patient spécifique, 
     * en utilisant l'identifiant du patient fourni dans l'URL et les paramètres de pagination spécifiés dans la requête.
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Page<ConsultationDTO>> getConsultationsByPatient(
            @PathVariable Long patientId,
            Pageable pageable) {
        return ResponseEntity.ok(consultationService.getConsultationsByPatient(patientId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable Long id) {
        ConsultationDTO consultation = consultationService.getConsultationById(id);
        return ResponseEntity.ok(consultation);
    }
/*
 * Enrégistre une consultation
 */
    @PostMapping
    public ResponseEntity<ConsultationDTO> addConsultation(@RequestBody ConsultationDTO consultationDTO) {
        ConsultationDTO savedConsultation = consultationService.addConsultation(consultationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConsultation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultation(@PathVariable Long id, @RequestBody ConsultationDTO consultationDTO) {
        ConsultationDTO updatedConsultation = consultationService.updateConsultation(id, consultationDTO);
        return ResponseEntity.ok(updatedConsultation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }
}
