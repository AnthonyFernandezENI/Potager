package fr.anthomane.potager.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.anthomane.potager.bll.PotagerManager;
import fr.anthomane.potager.bll.PotagerManagerException;
import fr.anthomane.potager.bo.Carre;
import fr.anthomane.potager.bo.Implantation;
import fr.anthomane.potager.bo.Plante;
import fr.anthomane.potager.bo.Potager;

@Controller
public class PotagerController {
	
	@Autowired
	PotagerManager manager;
	
	@GetMapping("/potager")
	public String index(Model model) {
		model.addAttribute("message", "Salut !");
		return "index";
	}
	
	@GetMapping("potager/liste")
	public String potagerListe(Model model) {
		model.addAttribute("potagers", manager.getAllPotagers());
		return "potager/potagerList";
	}
	
	@GetMapping("potager/add")
	public String potagerAdd(Potager p, Model model) {
		return "potager/potagerAdd";
	}
	
	@PostMapping("potager/add")
	public String potagerSubmit(@Valid Potager p, BindingResult result, Model model) {
		 if (result.hasErrors()) {
			 return "potager/potagerAdd";
		 }else {
			 manager.addPotager(p);
			 model.addAttribute("message","Potager " + p.getNom() + " ajouté avec succès");
			 return "redirect:/potager/liste";
		 }
	}
	
	@GetMapping("potager/{id}")
	public String potagerDetail(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("potager", manager.getPotagerById(id));
		return "potager/potagerDetail";
	}
	
	@GetMapping("potager/{id}/add")
	public String carreAdd(@PathVariable("id") Integer id, Carre c, Model model) {
		Potager p = manager.getPotagerById(id);
		c.setPotager(p);
		model.addAttribute("potager", p);
		return "carre/carreAdd";
	}
	/**
	 * Traite le formulaire d'ajout d'un carré
	 * @param c le carré généré par le formulaire
	 * @param result BindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("carre/add")
	public String carreSubmit(@Valid Carre c, BindingResult result, Model model) {
		 if (result.hasErrors()) {
			 return "carre/carreAdd";
		 }else {
			 try {
				manager.addCarre(c);
				model.addAttribute("message","Carré " + c.getIdCarre() + " ajouté avec succès");
			} catch (PotagerManagerException e) {
				e.printStackTrace();
			}
			 return "redirect:/potager/" + c.getPotager().getIdPotager();
		 }
	}
	
	@GetMapping("carre/{id}")
	public String carreDetail(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("carre", manager.getCarreById(id));
		return "carre/carreDetail";
	}
	
	@GetMapping("carre/{id}/add")
	public String implantationAdd(@PathVariable("id") Integer id, Implantation i, Model model) {
		Carre c = manager.getCarreById(id);
		i.setCarre(c);
		model.addAttribute("carre", c);
		model.addAttribute("plantes", manager.getAllPlantes());
		return "implantation/implantationAdd";
	}
	
	@PostMapping("implantation/add")
	public String implantationSubmit(@Valid Implantation i, BindingResult result, Model model) {
		System.err.println(result); 
		if (result.hasErrors()) {
			 model.addAttribute("carre", i.getCarre());
			 model.addAttribute("plantes", manager.getAllPlantes());
			 return "implantation/implantationAdd";
		 }else {
			 try {
				manager.addImplantation(i);
				model.addAttribute("message","Implantation ajoutée avec succès");
			} catch (PotagerManagerException e) {
				e.printStackTrace();
			}
			 return "redirect:/carre/" + i.getCarre().getIdCarre();
		 }
	}
	
	
	@GetMapping("plante/liste")
	public String planteListe(Model model) {
		model.addAttribute("plantes", manager.getAllPlantes());
		return "plante/planteList";
	}
	
	@GetMapping("plante/{id}")
	public String planteDetail(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("plante", manager.getPlanteById(id));
		return "plante/planteDetail";
	}
	
	@GetMapping("plante/add")
	public String planteAdd(Plante p, Model model) {
		return "plante/planteAdd";
	}
	
	@PostMapping("plante/add")
	public String planteSubmit(@Valid Plante p, BindingResult result, Model model) {
		 if (result.hasErrors()) {
			 return "plante/planteAdd";
		 }else {
			 try {
				manager.addPlante(p);
				model.addAttribute("message","Plante " + p.getNom() + " ajouté avec succès");
			} catch (PotagerManagerException e) {
				e.printStackTrace();
			}
			 return "redirect:/plante/liste";
		 }
	}

	
	
	
	
	
}
