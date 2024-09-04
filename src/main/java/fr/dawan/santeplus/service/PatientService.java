package fr.dawan.santeplus.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.dawan.santeplus.dto.PatientDTO;
import fr.dawan.santeplus.entities.Patient;
import fr.dawan.santeplus.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PatientDTO> getAllPatients(String nomPAT, String numeroSS, Pageable pageable) {
        Page<Patient> patients = patientRepository.findByNomPATContainingOrNumeroSSContaining(nomPAT, numeroSS, pageable);
        return patients.map(patient -> modelMapper.map(patient, PatientDTO.class));
    }

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + id));
        return modelMapper.map(patient, PatientDTO.class);
    }

    public PatientDTO addPatient(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        Patient savedPatient = patientRepository.save(patient);
        return modelMapper.map(savedPatient, PatientDTO.class);
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + id));
        modelMapper.map(patientDTO, patient);
        Patient updatedPatient = patientRepository.save(patient);
        return modelMapper.map(updatedPatient, PatientDTO.class);
    }

    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + id));
        patientRepository.delete(patient);
    }
}
