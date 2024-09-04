package fr.dawan.santeplus.dto;

import java.util.List;

import fr.dawan.santeplus.entities.Consultation;
import fr.dawan.santeplus.entities.Medicament;
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
public class PrescriptionDTO {
	
	private Long id;
    private String nbPrises;
    private MedicamentDTO medicament;
    
}