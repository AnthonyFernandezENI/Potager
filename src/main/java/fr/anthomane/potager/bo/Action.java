package fr.anthomane.potager.bo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Action {
	@Id
	@GeneratedValue
	private Integer idAction;
	private LocalDate dateAction;
	private String evenement;
	@ManyToOne
	private Carre carre;
	
	public Action(LocalDate dateAction, String evenement, Carre carre) {
		super();
		this.dateAction = dateAction;
		this.evenement = evenement;
		this.carre = carre;
	}	
}
