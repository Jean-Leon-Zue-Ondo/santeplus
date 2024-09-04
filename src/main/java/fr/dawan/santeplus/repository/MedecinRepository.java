package fr.dawan.santeplus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.santeplus.entities.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
	/*
	 * 
	 */
	
    Page<Medecin> findByNomMEDContainingOrMatriculeContaining(String nomMED, String matricule, Pageable pageable);
}
