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
	 * @throws PotagerManagerException 
	 */
	public void addCarre(Carre c) throws PotagerManagerException;
	/**
	 * Récupère tous les carrés en BDD
	 * @return Une liste de tous les carrés de tous les potagers
	 */
	public List<Carre> getAllCarres();
	public List<Carre> getAllCarresByPotager(Potager p);
	public void updateCarre(Carre c);
	public void deleteCarre(Carre c);
	
	public void addPlante(Plante p) throws PotagerManagerException;
	public List<Plante> getAllPlantes();
	public void updatePlante(Plante p);
	public void deletePlante(Plante p);
	
	/**
	 * Ajoute une plante dans un carré
	 * @param i instance d'implantation
	 * @throws PotagerManagerException 
	 */
	public void addImplantation(Implantation i) throws PotagerManagerException;
	
	/**
	 * Récupère toutes les implantations d'un carré
	 * @param c carré ciblé
	 * @return toutes les implantations d'un carré
	 */
	public List<Implantation> getAllImplantationByCarre(Carre c);
	
	/**
	 * Ajoute une action en BDD
	 * @param a action concernée
	 * @throws PotagerManagerException 
	 */
	public void addAction(Action a) throws PotagerManagerException;
	/**
	 * Récupère les actions des deux prochaines semaines
	 * @return les actions des deux prochaines semaines
	 */
	public List<Action> getAllActionsForTwoWeeks();
	
	/**
	 * 
	 * @param p plante ciblée
	 * @return Une liste d'Implantation concernant la plante
	 */
	public List<Implantation> getLocalisationByPlante(Plante p);
	public Plante getPlanteById(Integer id);
	public Potager getPotagerById(Integer id);
	public Carre getCarreById(Integer id);
	public Implantation getImplantationById(Integer id);
	public void deleteImplantation(Implantation i);
}
