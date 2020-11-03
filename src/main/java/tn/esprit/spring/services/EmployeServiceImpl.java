package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	private final Logger log = LoggerFactory.getLogger(EmployeServiceImpl.class);

	public int ajouterEmploye(Employe employe) {
		log.debug("request to save Employe:", employe);
		try {
			employeRepository.save(employe);
			log.info("employee has been add with succes !!");
			return employe.getId();

		} catch (Exception e) {
			log.error("error methode add employe" + e);
			return -1;
		}
	}


	public int mettreAjourEmailByEmployeId(String email, int employeId) {
		log.debug("request to update Employe:", employeId);
		try {

			Optional<Employe> value = employeRepository.findById(employeId);
			if (value.isPresent()) {

				Employe employe = value.get();
				log.debug("EmployeById." + employe);


				employe.setEmail(email);

				employeRepository.save(employe);
				log.info("mettreAjourEmailByEmployeId done!!!! ");


				return 1;
			}
			return 1;
		} catch (Exception e) {
			log.error("error methode add mettreAjourEmailByEmployeId" + e);
			return -1;
		}

	}




	public int getNombreEmployeJPQL() {
		log.debug("request to get Nombre Employe:");
		try {
int i= employeRepository.countemp();
			log.info("getNombreEmployeJPQL done!!!! ");
			return i;
		}catch (Exception e){
			log.error("error methode getNombreEmployeJPQL " +e);
			return -1;
		}

	}

	public List<String> getAllEmployeNamesJPQL() {
		log.debug("request to get all Employe by names:");
		try {
			List<String > list= employeRepository.employeNames();
			log.info("getAllEmployeNamesJPQL don !! ");
			return list;

		} catch (Exception e ){
			log.error("error methode getNombreEmployeJPQL " +e);
			return null ;
		}


	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		log.debug("request to get salaire Employe by id:",employeId);
		try {
			float a= employeRepository.getSalaireByEmployeIdJPQL(employeId);
			log.info("getSalaireByEmployeIdJPQL done !!");

			return a;

		} catch (Exception e ){
			log.error("error methode getSalaireByEmployeIdJPQL" +e);
			return -1;
		}

	}


	public List<Employe> getAllEmployes() {
		log.debug("request to get all Employes:");
		try {

			return (List<Employe>) employeRepository.findAll();
			//log.info("getAllEmployes done!!");
		} catch (Exception e) {
			log.error("error methodeg etAllEmployes" + e);
			return null ;
		}

	}


	public int affecterContratAEmploye(int contratId, int employeId) {
		log.debug("request to affecter Contrat by id{} to Employe by id {}:",contratId,employeId);
		try {
			log.info("affecterContratAEmploye");
			Optional<Contrat> contratManagedEntity =contratRepoistory.findById(contratId);
			Optional<Employe> value = employeRepository.findById(employeId);
	if (contratManagedEntity.isPresent()){
		Contrat contrat=contratManagedEntity.get();
		if (value.isPresent()){
			Employe employe=value.get();
			contrat.setEmploye(employe);

		}
		contratRepoistory.save(contrat);

	}
			log.info("affecterContratAEmploye done !!");
			return 1;


		}catch (Exception e){
			log.error("rror methode affecterContratAEmploye" +e);
			return -1;
		}


	}

}
