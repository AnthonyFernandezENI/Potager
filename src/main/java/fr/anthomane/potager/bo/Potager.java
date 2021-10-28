package fr.anthomane.potager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotBlank
	private String localisation;
	@NotBlank
	private String nom;
	@NotNull
	private Double surface;
	@NotBlank
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
