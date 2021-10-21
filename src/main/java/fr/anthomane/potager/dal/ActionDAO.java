package fr.anthomane.potager.dal;

import org.springframework.data.repository.CrudRepository;

import fr.anthomane.potager.bo.Action;

public interface ActionDAO extends CrudRepository<Action, Integer>{

}
