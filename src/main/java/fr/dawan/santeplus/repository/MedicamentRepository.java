package fr.dawan.santeplus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.santeplus.entities.Medicament;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

	Optional<Medicament> findByCode(String code);}
