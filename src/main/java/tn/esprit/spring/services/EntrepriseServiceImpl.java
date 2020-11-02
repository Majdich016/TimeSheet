package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	private static final Logger logger = Logger.getLogger(EntrepriseServiceImpl.class);
	
	public int ajouterEntreprise(Entreprise entreprise) {
		try {
		logger.debug("je viens de lacer l'ajout d'une entreprise. ");

		entrepriseRepoistory.save(entreprise);

		logger.info("ajout terminé avec succé !!!");

		}catch (Exception e){
			logger.error("Erreur dans ajouterEntreprise(): "+ e);
		}finally {
			logger.info("Méthode ajouterEntreprise() finie...!!!!");
		}

		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		try {
			logger.debug("je viens de lacer l'ajout d'un deparetemnt. ");

			deptRepoistory.save(dep);

			logger.info("ajout terminé avec succé !!!");

		}catch (Exception e){
			logger.error("Erreur dans ajoutDepartement(): "+ e);
		}finally {
			logger.info("Méthode ajouterDepartement() finie...!!!!");
		}

		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {

		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.

		try {

			logger.debug("je viens de lacer l'affectation d'un deparetemnt à une entreprise. ");

				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);

				logger.info("le departement et bien affécté à l'entreprise !!");

		}catch (Exception e){
			logger.error("Erreur dans affecterDepartementAEntreprise(): "+ e);
		}finally {
			logger.info("Méthode affecterDepartementAEntreprise() finie...!!!!");
		}
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {

		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();

		try {

		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}

			logger.info("les departement sont récupérés de la liste des entreprises");

		} catch (Exception e) {
			logger.error("Erreur dans getAllDepartementsNamesByEntreprise(): " + e);
		} finally {
			logger.info("Méthode getAllDepartementsNamesByEntreprise() finie...!!!!");
		}

		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		try {

			logger.info("Entreprise "+entrepriseRepoistory.findById(entrepriseId).get().getName()
					+" est supprimé de la liste des entreprise");

		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());

		}catch (Exception e){
			logger.error("Erreur dans deleteEntrepriseById(): "+ e);
		} finally {
			logger.info("Méthode deleteEntreprise() finie...!!!!");
		}
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		try {

			logger.info("Departement "+deptRepoistory.findById(depId).get().getName()
					+"est supprimé de la liste des departements");

		deptRepoistory.delete(deptRepoistory.findById(depId).get());

		}catch (Exception e){
			logger.error("Erreur dans deleteDepartementById(): "+ e);
		} finally {
			logger.info("Méthode deleteDepartementById() finie...!!!!");
		}

		}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Entreprise entreprise = new Entreprise();
		try {
			entreprise = entrepriseRepoistory.findById(entrepriseId).get();

			logger.info("Entreprise " + entreprise.getName()
					+ " est récupérer de la liste des entreprises");

		} catch (Exception e) {
			logger.error("Erreur dans getEntrepriseById(): " + e);
		} finally {
			logger.info("Méthode getEntrepriseById() finie...!!!!");
		}

		return entreprise;
	}

}
