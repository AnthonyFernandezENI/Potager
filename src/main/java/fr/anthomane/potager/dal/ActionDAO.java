package fr.anthomane.potager.dal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.anthomane.potager.bo.Action;

public interface ActionDAO extends CrudRepository<Action, Integer>{
	@Query("SELECT a FROM Action a WHERE a.dateAction BETWEEN ?1 AND ?2")
	List<Action> findAllForTwoWeeks(LocalDate now, LocalDate limite);

}
