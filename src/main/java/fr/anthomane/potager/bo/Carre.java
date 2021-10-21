package fr.anthomane.potager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Carre {
	
	@Id
	@GeneratedValue
	private Integer idCarre;
	@ManyToOne
	private Potager potager;
	private Double surface;
	private String typeSol;
	private String typeExposition;
	
	public Carre(Potager potager, Double surface, String typeSol, String typeExposition) {
		super();
		this.potager = potager;
		this.surface = surface;
		this.typeSol = typeSol;
		this.typeExposition = typeExposition;
	}
}
