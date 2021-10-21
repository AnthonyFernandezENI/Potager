package fr.anthomane.potager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Potager {
	
	@Id
	@GeneratedValue
	private Integer idPotager;
	private String localisation;
	private String nom;
	private Double surface;
	private String ville;
	
	public Potager(String localisation, String nom, Double surface, String ville) {
		super();
		this.localisation = localisation;
		this.nom = nom;
		this.surface = surface;
		this.ville = ville;
	}
}
