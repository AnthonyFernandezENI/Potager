package fr.anthomane.potager.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.anthomane.potager.bo.Action;
import fr.anthomane.potager.bo.Carre;
import fr.anthomane.potager.bo.Implantation;
import fr.anthomane.potager.bo.Plante;
import fr.anthomane.potager.bo.Potager;
import fr.anthomane.potager.dal.ActionDAO;
import fr.anthomane.potager.dal.CarreDAO;
import fr.anthomane.potager.dal.ImplantationDAO;
import fr.anthomane.potager.dal.PlanteDAO;
import fr.anthomane.potager.dal.PotagerDAO;

@Service
public class PotagerManagerImpl implements PotagerManager {

	@Autowired
	PotagerDAO potagerDao;

	@Autowired
	PlanteDAO planteDao;

	@Autowired
	ImplantationDAO implantationDao;

	@Autowired
	CarreDAO carreDao;

	@Autowired
	ActionDAO actionDao;

	@Override
	@Transactional
	public void addPotager(Potager p) {
		potagerDao.save(p);
	}

	@Override
	@Transactional
	public List<Potager> getAllPotagers() {
		return (List<Potager>) potagerDao.findAll();
	}

	@Override
	@Transactional
	public void updatePotager(Potager p) {
		potagerDao.save(p);

	}

	@Override
	@Transactional
	public void deletePotager(Potager p) {
		List<Carre> carres = carreDao.findAllByPotager(p);
		for (Carre carre : carres) {
			List<Implantation> implantations = implantationDao.findAllByCarre(carre);
			for (Implantation implantation : implantations) {
				implantationDao.delete(implantation);
			}
			carreDao.delete(carre);
		}
		potagerDao.delete(p);
	}

	@Override
	@Transactional
	public void addCarre(Carre c) throws PotagerManagerException {
		// la somme des tailles des carrés doit etre inférieur à celle du potager
		Potager p = c.getPotager();
		double surfaceDisponible = p.getSurface();
		List<Carre> lstCarreDuPotager = carreDao.findAllByPotager(p); // fait une liste des carrés actuellement dans le potager
		if (carreDao.findAllByPotager(p) != null) {
			for (Carre carre : lstCarreDuPotager) {
				surfaceDisponible -= carre.getSurface();
			}
		}
		if ((surfaceDisponible - c.getSurface()) > 0) {
			carreDao.save(c);
		} else {
			throw new PotagerManagerException("Il n'y a pas assez de place dans ce potager. " + "Surface disponible : "
					+ surfaceDisponible + " cm², nécessaire : " + c.getSurface() + " cm².");
		}
	}

	@Override
	@Transactional
	public List<Carre> getAllCarres() {
		return (List<Carre>) carreDao.findAll();
	}

	@Override
	@Transactional
	public List<Carre> getAllCarresByPotager(Potager p) {
		return carreDao.findAllByPotager(p);
	}

	@Override
	@Transactional
	public void updateCarre(Carre c) {
		carreDao.save(c);
	}

	@Override
	@Transactional
	public void deleteCarre(Carre c) {
		List<Implantation> implantations = implantationDao.findAllByCarre(c);
		for (Implantation implantation : implantations) {
			implantationDao.delete(implantation);
		}
		carreDao.delete(c);
	}

	@Override
	@Transactional
	public void addPlante(Plante p) throws PotagerManagerException {
		Plante test = planteDao.findIfAlreadyExist(p.getNom(), p.getVariete());
		if (test == null) {
			planteDao.save(p);
		} else {
			throw new PotagerManagerException(
					"La plante " + p.getNom() + " de variété " + p.getVariete() + " exite déjà.");
		}

	}

	@Override
	@Transactional
	public List<Plante> getAllPlantes() {
		return (List<Plante>) planteDao.findAll();
	}

	@Override
	@Transactional
	public void updatePlante(Plante p) {
		planteDao.save(p);
	}

	@Override
	@Transactional
	public void deletePlante(Plante p) {
		implantationDao.removeAllByPlante(p);
		planteDao.delete(p);
	}

	@Override
	@Transactional
	public void addImplantation(Implantation i) throws PotagerManagerException {
		Carre c = i.getCarre(); // Récupère le carré de l'implantation
		List<Plante> lstPlante = new ArrayList<>(); // Liste des plantes déjà plantées
		double surfaceDisponible = c.getSurface(); // Récupère la surface disponible initialement sur le carré
		List<Implantation> lstImplantationDuCarre = implantationDao.findAllByCarre(c); // Récupère une liste des
																						// implantations déjà présentes
		for (Implantation implantation : lstImplantationDuCarre) {
			// Retire la place occupée par chaque implantation de la surface disponible
			surfaceDisponible -= implantation.getPlante().getSurface() * implantation.getQuantite();

			// Si la liste des plantes ne contient pas la plante, on l'ajoute
			if (!lstPlante.contains(implantation.getPlante())) {
				lstPlante.add(implantation.getPlante());
			}
		}
		// Si la place restante après ajout de l'implantation n'est pas négative, il y a
		// assez de place
		if ((surfaceDisponible - (i.getPlante().getSurface() * i.getQuantite())) >= 0) {

			// s'il y a moins de 3 plantes différentes sur ce carré, l'implantation d'une nouvelle est possible
			if (lstPlante.size() < 3) {
				implantationDao.save(i);
				// Si pas de place (déjà 3 plantes), affichage d'erreur
			} else {
				throw new PotagerManagerException("Pas plus de trois plantes différentes dans le même carré");
			}

		}
		// Si pas de place (superficie insuffisante), affichage d'erreur
		else {
			throw new PotagerManagerException(
					"Il n'y a pas assez de place dans ce carré. " + "Surface disponible : " + surfaceDisponible
							+ " cm², nécessaire : " + i.getPlante().getSurface() * i.getQuantite() + " cm².");
		}

	}

	@Override
	@Transactional
	public List<Implantation> getAllImplantationByCarre(Carre c) {
		return implantationDao.findAllByCarre(c);
	}

	@Override
	@Transactional
	public void addAction(Action a) throws PotagerManagerException {
		if (a.getDateAction().isAfter(LocalDate.now())) {
			System.out.println("Votre événement " + a.getEvenement() + " pour le carré " + a.getCarre().getIdCarre()
					+ " du potager " + a.getCarre().getPotager().getNom() + " a bien été planifié pour le "
					+ a.getDateAction());
			actionDao.save(a);
		} else {
			throw new PotagerManagerException("La date de votre événement doit être supérieure à la date du jour.");
		}

	}

	@Override
	@Transactional
	public List<Action> getAllActionsForTwoWeeks() {
		return actionDao.findAllForTwoWeeks(LocalDate.now(), LocalDate.now().plusWeeks(2));
	}

	@Override
	@Transactional
	public List<Implantation> getLocalisationByPlante(Plante p) {
		return implantationDao.findAllByPlante(p);
	}

	@Override
	@Transactional
	public Plante getPlanteById(Integer id) {
		return planteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Potager getPotagerById(Integer id) {
		return potagerDao.findById(id).orElse(null);
	}

	@Override
	public Carre getCarreById(Integer id) {
		return carreDao.findById(id).orElse(null);
	}

	@Override
	public Implantation getImplantationById(Integer id) {
		return implantationDao.findById(id).orElse(null);
	}

	@Override
	public void deleteImplantation(Implantation i) {
		implantationDao.delete(i);
		
	}
}
