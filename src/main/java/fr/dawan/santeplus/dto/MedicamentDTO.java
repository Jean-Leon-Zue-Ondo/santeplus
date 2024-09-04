package fr.dawan.santeplus.dto;

import java.util.Set;

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
public class MedicamentDTO {
	/*
	 * 
	 */
	private Long id;
    private String code;
    private String libelle;
}
