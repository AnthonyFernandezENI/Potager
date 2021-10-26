package fr.anthomane.potager.dal;

import org.springframework.data.repository.CrudRepository;

import fr.anthomane.potager.bo.Potager;

public interface PotagerDAO extends CrudRepository<Potager, Integer> {

}
