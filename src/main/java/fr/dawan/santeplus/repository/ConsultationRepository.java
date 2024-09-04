package fr.dawan.santeplus.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.santeplus.dto.ConsultationDTO;
import fr.dawan.santeplus.entities.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
	/*
	 * 
	 */
    Page<Consultation> findByPatientId(Long patientId, Pageable pageable);
}
