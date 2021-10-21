package fr.anthomane.potager.bll;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

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
	public void addCarre(Carre c) {
	carreDao.save(c);
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
	public void addPlante(Plante p) {
		planteDao.save(p);
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
		planteDao.delete(p);
	}

	@Override
	@Transactional
	public void addImplantation(Implantation i) {
		implantationDao.save(i);
	}

	@Override
	@Transactional
	public List<Implantation> getAllImplantationByCarre(Carre c) {
		return implantationDao.findAllByCarre(c);
	}

	@Override
	@Transactional
	public void addAction(Action a) {
		actionDao.save(a);
	}

}
