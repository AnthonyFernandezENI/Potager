package fr.anthomane.potager.bll;

import java.time.LocalDate;
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
		potagerDao.delete(p);
	}

	@Override
	@Transactional
	public void addCarre(Carre c) throws PotagerManagerException {
		// la somme des tailles des carrés doit etre inférieur à celle du potager
		Potager p = c.getPotager();
		double surfaceDisponible = p.getSurface();
		List<Carre> lstCarreDuPotager = carreDao.findAllByPotager(p); //fait une liste des carrés actuellement dans le potager
		for (Carre carre : lstCarreDuPotager) {
			surfaceDisponible -= carre.getSurface();
		}
		if ((surfaceDisponible - c.getSurface()) > 0) {
			carreDao.save(c);
		}
		else {
			throw new PotagerManagerException ("Il n'y a pas assez de place dans ce potager. "
					+ "Surface disponible : "+surfaceDisponible+" cm², nécessaire : " + c.getSurface() +" cm².");
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
		carreDao.delete(c);
	}

	@Override
	@Transactional
	public void addPlante(Plante p) throws PotagerManagerException {
		Plante test = planteDao.findIfAlreadyExist(p.getNom(), p.getVariete());
		if (test == null) {
			planteDao.save(p);
		} else {
			throw new PotagerManagerException("La plante " + p.getNom() + " de variété " + p.getVariete() + " exite déjà.");
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
		Carre c = i.getCarre();
		double surfaceDisponible = c.getSurface();
		List <Implantation> lstImplantationDuCarre = implantationDao.findAllByCarre(c);
		for (Implantation implantation : lstImplantationDuCarre) {
			surfaceDisponible -= implantation.getPlante().getSurface() * implantation.getQuantite();
		}
		if ((surfaceDisponible - (i.getPlante().getSurface() * i.getQuantite())) >= 0) {
			
			//TODO Ajouter une vérification de doublon ET s'il y a moins de 3 plantes différentes sur ce carré
			implantationDao.save(i);
		}
		else {
			throw new PotagerManagerException("Il n'y a pas assez de place dans ce carré. "
					+ "Surface disponible : "+surfaceDisponible+" cm², nécessaire : " + i.getPlante().getSurface() * i.getQuantite() +" cm².");
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
					+ " du potager " + a.getCarre().getPotager().getNom() + " a bien été planifié pour le " + a.getDateAction());
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
}
