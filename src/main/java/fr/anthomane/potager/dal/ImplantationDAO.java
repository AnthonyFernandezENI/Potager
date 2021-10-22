package fr.anthomane.potager.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.anthomane.potager.bo.Carre;
import fr.anthomane.potager.bo.Implantation;
import fr.anthomane.potager.bo.Plante;

public interface ImplantationDAO extends CrudRepository<Implantation, Integer>{
	@Query("SELECT i FROM Implantation i WHERE i.carre = :carre")
	List<Implantation> findAllByCarre(@Param("carre") Carre carre);
	
	@Query("SELECT i FROM Implantation i WHERE i.plante = :plante")
	List<Implantation> findAllByPlante(@Param("plante") Plante plante);
	
	@Modifying
	@Query("DELETE FROM Implantation i WHERE i.plante = :plante")
	void removeAllByPlante(@Param("plante") Plante plante);
}
