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

import fr.dawan.santeplus.dto.PatientDTO;
import fr.dawan.santeplus.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    
    @Autowired
    private PatientService patientService;

    /*
     * permet de récupérer une liste paginée de patients. 
     */
    @GetMapping
    public Page<PatientDTO> getAllPatients(@RequestParam(required = false) String nomPAT,   // paramètre de requete pour filtrer par le nom du patient
                                           @RequestParam(required = false) String numeroSS,  // paramètre de requete pour filtrer par le numéro de securité sociale
                                           Pageable pageable) {   // contient les informations de pagination : la taille le numéro de page ...
        return patientService.getAllPatients(nomPAT, numeroSS, pageable); // patientService.getAllPatients implémente et intéroge la base de donnée : le retour de la méthode renvois une page les données de PatienDTO
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping // permet d'enrégistre un patient : un objet PatientDTO est envoyé depuis le corps de la méthode HTTPs pour être enrégistré par la base de bonnée par le service 
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO savedPatient = patientService.addPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @PutMapping("/{id}") // permet de mettre à jour/modifier les données d'un patient : 
    //attend un (id) dans l'URL selectionner quel patient modiffié dans la base de donnée
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) { //@PathVariable Long id: Ce paramètre extrait l'ID du patient de l'URL. Cet ID est utilisé pour identifier le patient spécifique dans la base de données.
        PatientDTO updatedPatient = patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
