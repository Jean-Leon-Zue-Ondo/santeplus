package fr.dawan.santeplus.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.dawan.santeplus.entities.Consultation;
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
public class PatientDTO {
	
	private Long id;
    private String numeroSS;
    private String nomPAT;
   
}