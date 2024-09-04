package fr.dawan.santeplus.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Table(name="medecin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medecin {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricule;
    private String nomMED;

    @OneToMany(mappedBy = "medecin")
    private List<Consultation> consultations;

}
