package fr.dawan.santeplus.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.santeplus.dto.MedicamentDTO;
import fr.dawan.santeplus.entities.Medicament;
import fr.dawan.santeplus.repository.MedicamentRepository;

@Service
public class MedicamentService {

    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MedicamentDTO> getAllMedicaments() {
        List<Medicament> medicaments = medicamentRepository.findAll();
        return medicaments.stream()
                .map(medicament -> modelMapper.map(medicament, MedicamentDTO.class))
                .collect(Collectors.toList());
    }

    public MedicamentDTO getMedicamentByCode(String code) {
        Medicament medicament = medicamentRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Medicament not found for this code :: " + code));
        return modelMapper.map(medicament, MedicamentDTO.class);
    }

    public MedicamentDTO addMedicament(MedicamentDTO medicamentDTO) {
        Medicament medicament = modelMapper.map(medicamentDTO, Medicament.class);
        Medicament savedMedicament = medicamentRepository.save(medicament);
        return modelMapper.map(savedMedicament, MedicamentDTO.class);
    }

    public MedicamentDTO updateMedicament(String code, MedicamentDTO medicamentDTO) {
        Medicament medicament = medicamentRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Medicament not found for this code :: " + code));
        modelMapper.map(medicamentDTO, medicament);
        Medicament updatedMedicament = medicamentRepository.save(medicament);
        return modelMapper.map(updatedMedicament, MedicamentDTO.class);
    }

    public void deleteMedicament(String code) {
        Medicament medicament = medicamentRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Medicament not found for this code :: " + code));
        medicamentRepository.delete(medicament);
    }

	
}
