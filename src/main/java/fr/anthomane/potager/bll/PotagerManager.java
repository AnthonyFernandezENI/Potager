package fr.anthomane.potager.bll;

import java.util.List;

import fr.anthomane.potager.bo.Action;
import fr.anthomane.potager.bo.Carre;
import fr.anthomane.potager.bo.Implantation;
import fr.anthomane.potager.bo.Plante;
import fr.anthomane.potager.bo.Potager;

public interface PotagerManager {
	public void addPotager(Potager p);
	public List<Potager> getAllPotagers();
	public void updatePotager(Potager p);
	public void deletePotager(Potager p);
	
	/**
	 * Ajoute un carré 
	 * @param c carré à ajouter
	 */
	public void addCarre(Carre c);
	/**
	 * Récupère tous les carrés en BDD
	 * @return Une liste de tous les carrés de tous les potagers
	 */
	public List<Carre> getAllCarres();
	public List<Carre> getAllCarresByPotager(Potager p);
	public void updateCarre(Carre c);
	public void deleteCarre(Carre c);
	
	public void addPlante(Plante p);
	public List<Plante> getAllPlantes();
	public void updatePlante(Plante p);
	public void deletePlante(Plante p);
	
	/**
	 * Ajoute une plante dans un carré
	 * @param i instance d'implantation
	 */
	public void addImplantation(Implantation i);
	
	/**
	 * Récupère toutes les implantations d'un carré
	 * @param c carré ciblé
	 * @return toutes les implantations d'un carré
	 */
	public List<Implantation> getAllImplantationByCarre(Carre c);
	
	/**
	 * Ajoute une action en BDD
	 * @param a action concernée
	 */
	public void addAction(Action a);
}
