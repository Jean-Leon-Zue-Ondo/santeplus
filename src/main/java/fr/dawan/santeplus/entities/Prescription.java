package fr.dawan.santeplus.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name="prescrit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nbPrises;

    @ManyToOne
    @JoinColumn(name = "medicament_id")
    private Medicament medicament;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;
	
}
