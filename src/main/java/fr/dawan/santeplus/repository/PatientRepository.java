package fr.dawan.santeplus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.santeplus.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
    Page<Patient> findByNomPATContainingOrNumeroSSContaining(String nomPAT, String numeroSS, Pageable pageable);
    
}
