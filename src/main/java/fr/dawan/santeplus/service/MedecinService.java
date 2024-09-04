package fr.dawan.santeplus.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.dawan.santeplus.dto.MedecinDTO;
import fr.dawan.santeplus.entities.Medecin;
import fr.dawan.santeplus.repository.MedecinRepository;


@Service
public class MedecinService {

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<MedecinDTO> getAllMedecins(String nomMED, String matricule, Pageable pageable) {
        Page<Medecin> medecins = medecinRepository.findByNomMEDContainingOrMatriculeContaining(nomMED, matricule, pageable);
        return medecins.map(medecin -> modelMapper.map(medecin, MedecinDTO.class));
    }

    public MedecinDTO getMedecinById(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin not found for this id :: " + id));
        return modelMapper.map(medecin, MedecinDTO.class);
    }

    public MedecinDTO addMedecin(MedecinDTO medecinDTO) {
        Medecin medecin = modelMapper.map(medecinDTO, Medecin.class);
        Medecin savedMedecin = medecinRepository.save(medecin);
        return modelMapper.map(savedMedecin, MedecinDTO.class);
    }

    public MedecinDTO updateMedecin(Long id, MedecinDTO medecinDTO) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin not found for this id :: " + id));
        modelMapper.map(medecinDTO, medecin);
        Medecin updatedMedecin = medecinRepository.save(medecin);
        return modelMapper.map(updatedMedecin, MedecinDTO.class);
    }

    public void deleteMedecin(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin not found for this id :: " + id));
        medecinRepository.delete(medecin);
    }
}
