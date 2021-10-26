package fr.anthomane.potager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = "potager")
@JsonIdentityInfo (
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idCarre")
public class Carre {
	
	@Id
	@GeneratedValue
	private Integer idCarre;
	@ManyToOne
	@JsonManagedReference
	private Potager potager;
	private Double surface;
	private String typeSol;
	private String typeExposition;
	
	@OneToMany (mappedBy = "carre")
	private List<Implantation> implantations = new ArrayList<>();
	
	public Carre(Potager potager, Double surface, String typeSol, String typeExposition) {
		super();
		this.potager = potager;
		this.surface = surface;
		this.typeSol = typeSol;
		this.typeExposition = typeExposition;
	}
		
	public void setPotager (Potager potager) {
		this.potager = potager;
		potager.getCarres().add(this);
	}
}
