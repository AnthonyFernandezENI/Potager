package fr.anthomane.potager.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.anthomane.potager.bo.Carre;
import fr.anthomane.potager.bo.Potager;

public interface CarreDAO extends CrudRepository<Carre, Integer> {
	@Query("SELECT c FROM Carre c WHERE c.potager = :potager")
	List<Carre> findAllByPotager(@Param("potager") Potager potager);
}
