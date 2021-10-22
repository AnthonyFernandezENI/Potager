package fr.anthomane.potager.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.anthomane.potager.bo.Plante;

public interface PlanteDAO extends CrudRepository<Plante, Integer>{
	@Query("FROM Plante p WHERE p.nom= ?1 AND p.variete= ?2")
	Plante findIfAlreadyExist(String nom, String variete);
}
