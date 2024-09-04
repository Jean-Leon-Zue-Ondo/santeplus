package fr.dawan.santeplus.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import fr.dawan.santeplus.entities.Medecin;
import fr.dawan.santeplus.entities.Patient;
import fr.dawan.santeplus.entities.Prescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultationDTO {
	
	private Long id;
    private String numero;
    private LocalDate date;
    private MedecinDTO medecin;
    private PatientDTO patient;
    private List<PrescriptionDTO> prescriptions;
	
}
