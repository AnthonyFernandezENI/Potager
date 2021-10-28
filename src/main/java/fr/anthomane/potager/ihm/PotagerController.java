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
		return "potagerList";
	}
	
	@GetMapping("potager/add")
	public String potagerAdd(Potager p, Model model) {
		return "potagerAdd";
	}
	
	@PostMapping("potager/add")
	public String potagerSubmit(@Valid Potager p, BindingResult result, Model model) {
		 if (result.hasErrors()) {
			 return "potagerAdd";
		 }else {
			 manager.addPotager(p);
			 model.addAttribute("message","Potager " + p.getNom() + " ajouté avec succès");
			 return "redirect:/potager/liste";
		 }
	}
	
	@GetMapping("potager/{id}")
	public String potagerDetail(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("potager", manager.getPotagerById(id));
		return "potagerDetail";
	}
	
	@GetMapping("potager/{id}/add")
	public String carreAdd(@PathVariable("id") Integer id, Carre c, Model model) {
		Potager p = manager.getPotagerById(id);
		c.setPotager(p);
		model.addAttribute("potager", p);
		return "carreAdd";
	}
	
	@PostMapping("carre/add")
	public String carreSubmit(@Valid Carre c, BindingResult result, Model model) {
		System.err.println(c.getPotager());
		 if (result.hasErrors()) {
			 return "carreAdd";
		 }else {
			 try {
				manager.addCarre(c);
				model.addAttribute("message","Carré " + c.getIdCarre() + " ajouté avec succès");
			} catch (PotagerManagerException e) {
				e.printStackTrace();
			}
			 return "redirect:/potager/liste";
		 }
	}
	
	@GetMapping("carre/{id}")
	public String carreDetail(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("carre", manager.getCarreById(id));
		return "carreDetail";
	}
	
	@GetMapping("plante/liste")
	public String planteListe(Model model) {
		model.addAttribute("plantes", manager.getAllPlantes());
		return "planteList";
	}
	
	@GetMapping("plante/{id}")
	public String planteDetail(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("plante", manager.getPlanteById(id));
		return "planteDetail";
	}

	
	
	
	
	
}
