package fr.dawan.santeplus.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.dawan.santeplus.dto.ConsultationDTO;
import fr.dawan.santeplus.entities.Consultation;
import fr.dawan.santeplus.entities.Patient;
import fr.dawan.santeplus.repository.ConsultationRepository;
import fr.dawan.santeplus.repository.PatientRepository;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ConsultationDTO> getConsultationsByPatient(Long patientId, Pageable pageable) {
        Page<Consultation> consultations = consultationRepository.findByPatientId(patientId, pageable);
        return consultations.map(consultation -> modelMapper.map(consultation, ConsultationDTO.class));
    }

    public ConsultationDTO getConsultationById(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found for this id :: " + id));
        return modelMapper.map(consultation, ConsultationDTO.class);
    }

    public ConsultationDTO addConsultation(ConsultationDTO consultationDTO) {
        Consultation consultation = modelMapper.map(consultationDTO, Consultation.class);
        
        // Vérifiez si le patient est déjà persisté
        Patient patient = consultation.getPatient();
        if (patient.getId() == null) {
            patient = patientRepository.save(patient);
            consultation.setPatient(patient);
        }

        Consultation savedConsultation = consultationRepository.save(consultation);
        return modelMapper.map(savedConsultation, ConsultationDTO.class);
    }

    public ConsultationDTO updateConsultation(Long id, ConsultationDTO consultationDTO) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found for this id :: " + id));
        modelMapper.map(consultationDTO, consultation);
        Consultation updatedConsultation = consultationRepository.save(consultation);
        return modelMapper.map(updatedConsultation, ConsultationDTO.class);
    }

    public void deleteConsultation(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found for this id :: " + id));
        consultationRepository.delete(consultation);
    }
}
