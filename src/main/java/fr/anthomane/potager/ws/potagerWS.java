package fr.anthomane.potager.ws;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.anthomane.potager.bll.PotagerManager;
import fr.anthomane.potager.bll.PotagerManagerException;
import fr.anthomane.potager.bo.Plante;
import fr.anthomane.potager.bo.Potager;

@RestController
public class potagerWS {

	@Autowired
	PotagerManager manager;
	
	@GetMapping("ws/potager/{id}")
	public Potager getPotager(@PathVariable("id") Integer id){
		return manager.getPotagerById(id);
	}

	@PostMapping("ws/plante")
	public List<Plante> add(@RequestBody Plante p) {
		try {
			manager.addPlante(p);
		} catch (PotagerManagerException e) {
			e.printStackTrace();
		}
		return manager.getAllPlantes();
	}
	
	@GetMapping("ws/plante/{id}")
	public Plante get(@PathVariable("id") Integer id) {
		return manager.getPlanteById(id);
	}
	
	@GetMapping("ws/plante")
	public List<Plante> getAll() {
		return manager.getAllPlantes();
	}
	
	@PutMapping("ws/plante")
	public List<Plante> update(@RequestBody Plante p) {
		manager.updatePlante(p);
		return manager.getAllPlantes();
	}	

	@DeleteMapping("ws/plante")
	public List<Plante> delete(@RequestBody Plante p) {
		manager.deletePlante(p);
		return manager.getAllPlantes();
	}
}
