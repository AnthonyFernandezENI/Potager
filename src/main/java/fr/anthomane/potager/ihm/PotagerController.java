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
	public String potagerSubmit(@Valid Potager p, BindingResult errors, Model model) {
		 if (errors.hasErrors()) {
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
