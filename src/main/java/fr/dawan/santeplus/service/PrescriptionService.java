package fr.dawan.santeplus.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.santeplus.dto.PrescriptionDTO;
import fr.dawan.santeplus.entities.Prescription;
import fr.dawan.santeplus.repository.PrescriptionRepository;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PrescriptionDTO getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found for this id :: " + id));
        return modelMapper.map(prescription, PrescriptionDTO.class);
    }


    public List<PrescriptionDTO> getPrescriptionsByConsultationId(Long consultationId) {
        List<Prescription> prescriptions = prescriptionRepository.findByConsultationId(consultationId);
        return prescriptions.stream()
                .map(prescription -> modelMapper.map(prescription, PrescriptionDTO.class))
                .collect(Collectors.toList());
    }

    public PrescriptionDTO addPrescription(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = modelMapper.map(prescriptionDTO, Prescription.class);
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return modelMapper.map(savedPrescription, PrescriptionDTO.class);
    }

    public PrescriptionDTO updatePrescription(Long id, PrescriptionDTO prescriptionDTO) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found for this id :: " + id));
        modelMapper.map(prescriptionDTO, prescription);
        Prescription updatedPrescription = prescriptionRepository.save(prescription);
        return modelMapper.map(updatedPrescription, PrescriptionDTO.class);
    }

    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found for this id :: " + id));
        prescriptionRepository.delete(prescription);
    }
}