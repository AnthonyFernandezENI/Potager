package fr.anthomane.potager.bll;

public class PotagerManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4214956600769937801L;

	public PotagerManagerException (String message) {
		super("Erreur : " + message);
	}
	
}
