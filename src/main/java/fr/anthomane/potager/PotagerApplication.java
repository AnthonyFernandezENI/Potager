package fr.anthomane.potager;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.anthomane.potager.bll.PotagerManager;
import fr.anthomane.potager.bll.PotagerManagerException;
import fr.anthomane.potager.bo.Action;
import fr.anthomane.potager.bo.Carre;
import fr.anthomane.potager.bo.Implantation;
import fr.anthomane.potager.bo.Plante;
import fr.anthomane.potager.bo.Potager;

@SpringBootApplication
public class PotagerApplication implements CommandLineRunner {

	@Autowired
	PotagerManager manager;
	
	public static void main(String[] args) {
		SpringApplication.run(PotagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Potager p1 = new Potager("Ici", "PoteJeune", 4500.0, "Quimper");
		Potager p2 = new Potager("Là bas", "Potofeu", 255200.5, "Châteaulin");
		Carre c1 = new Carre(p1, 500.0, "graniteux", "Pluie");
		Carre c2 = new Carre(p2, 400.0, "sablé", "Caniculaire");
		Carre c3 = new Carre(p2, 20000.0, "sablé", "Caniculaire");
		manager.addPotager(p1);
		manager.addPotager(p2);
		
		try {
			manager.addCarre(c1);
		} catch (PotagerManagerException e) {
			System.err.println(e.getMessage());
		}
		
		manager.addCarre(c2);
		manager.addCarre(c3);
//		manager.getAllCarresByPotager(p2).forEach(System.out::println);
		Action a1 = new Action(LocalDate.of(2021, 10, 23), "L'événement du 23", c2);	
		Action a3 = new Action(LocalDate.of(2111, 10, 23), "L'événement du futur !", c2);
		try {
			manager.addAction(a1);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			System.err.println(e2.getMessage());
		}
		try {
			manager.addAction(new Action(LocalDate.of(1911, 10, 23), "L'événement du passé", c2));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			System.err.println(e2.getMessage());
		}
		manager.addAction(a3);
//		manager.getAllActionsForTwoWeeks().forEach(System.out::println);
		
		Plante carotte = new Plante("Carotte", "Racine", "sables", 5.0);
		manager.addPlante(carotte);
		manager.addPlante(new Plante("Carotte", "Racine", "normale", 5.0));
		try {
			manager.addPlante(new Plante("Carotte", "Racine", "normale", 5.0));
		} catch (PotagerManagerException e1) {
			System.err.println(e1.getMessage());
		}
		
		Plante patate = new Plante("Pomme de terre", "Racine", "nouvelle", 3.0);
		manager.addPlante(patate);
		Implantation Imp1 = new Implantation(c1, carotte, 10, LocalDate.of(2021, 5, 6), LocalDate.of(2021, 8, 6));
		manager.addImplantation(Imp1);
		try {
			manager.addImplantation(new Implantation(c1, patate, 2, LocalDate.now() , LocalDate.now().plusMonths(2)));
		} catch (PotagerManagerException e) {
			System.err.println(e.getMessage());
		}
		Plante tom1 = new Plante("Tomate", "Fruit", "coeur de boeuf", 1.0);
		Plante tom2 = new Plante("Tomate", "Fruit", "cerise", 1.0);
		
		manager.addPlante(tom1);
		manager.addPlante(tom2);
		manager.addImplantation(new Implantation(c1, patate, 3, LocalDate.now() , LocalDate.now().plusMonths(2)));
		manager.addImplantation(new Implantation(c1, patate, 3, LocalDate.now() , LocalDate.now().plusMonths(2)));
		manager.addImplantation(new Implantation(c1, tom1, 3, LocalDate.now() , LocalDate.now().plusMonths(2)));
		try {
			manager.addImplantation(new Implantation(c1, tom2, 3, LocalDate.now() , LocalDate.now().plusMonths(2)));
		} catch (PotagerManagerException e) {
			System.err.println(e.getMessage());
		}
		
//		manager.getLocalisationByPlante(patate).forEach(System.out::println);
//		
//		manager.deletePlante(patate);
//		manager.getAllImplantationByCarre(c1).forEach(System.out::println);
		
	}

}
