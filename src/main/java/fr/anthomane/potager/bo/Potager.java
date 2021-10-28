package fr.anthomane.potager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo (
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idPotager")
public class Potager {
	
	@Id
	@GeneratedValue
	private Integer idPotager;
	private String localisation;
	private String nom;
	private Double surface;
	private String ville;
	
	@OneToMany(mappedBy = "potager")
	private List<Carre> carres = new ArrayList<>();
	
	
	public Potager(String localisation, String nom, Double surface, String ville) {
		super();
		this.localisation = localisation;
		this.nom = nom;
		this.surface = surface;
		this.ville = ville;
	}
}
