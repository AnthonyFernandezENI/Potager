package fr.anthomane.potager.dal;

import org.springframework.data.repository.CrudRepository;

import fr.anthomane.potager.bo.Plante;

public interface PlanteDAO extends CrudRepository<Plante, Integer>{

}
