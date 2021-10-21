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
	private LocalDate date;
	private String evenement;
	@ManyToOne
	private Carre carre;
	
	public Action(LocalDate date, String evenement, Carre carre) {
		super();
		this.date = date;
		this.evenement = evenement;
		this.carre = carre;
	}	
}
