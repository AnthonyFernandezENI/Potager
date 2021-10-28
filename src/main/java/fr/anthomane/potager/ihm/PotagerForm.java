package fr.anthomane.potager.ihm;

import fr.anthomane.potager.bo.Potager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PotagerForm {
	private Potager potager;
}
