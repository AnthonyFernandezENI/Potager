package fr.anthomane.potager.ihm;

import fr.anthomane.potager.bo.Implantation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ImplantationForm {
	private Implantation implantation;
	private Integer planteId;
	private Integer carreId;
}
