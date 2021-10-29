package fr.anthomane.potager.bo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@ToString (exclude = "carre")

public class Implantation {
	
	@Id 
	@GeneratedValue
	private Integer idImplantation;

	@ManyToOne
	@JsonManagedReference
	private Carre carre;
	@ManyToOne
	private Plante plante;
	private Integer quantite;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateMiseEnPlace;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateRecolte;
	
	public Implantation(Carre carre, Plante plante, Integer quantite, LocalDate dateMiseEnPlace,
			LocalDate dateRecolte) {
		super();
		this.carre = carre;
		this.plante = plante;
		this.quantite = quantite;
		this.dateMiseEnPlace = dateMiseEnPlace;
		this.dateRecolte = dateRecolte;
	}
}
