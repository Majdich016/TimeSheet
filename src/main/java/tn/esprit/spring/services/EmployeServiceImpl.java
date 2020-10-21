package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

		employeRepository.save(employe);

		return employe.getId();

	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		log.debug("request to update Employe:", employeId);
		Employe employe = employeRepository.findById(employeId).get();
		employe.setEmail(email);

		employeRepository.save(employe);

	}



	public int getNombreEmployeJPQL() {
		log.debug("request to get Nombre Employe:");
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		log.debug("request to get all Employe by names:");
		return employeRepository.employeNames();

	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		log.debug("request to get salaire Employe by id:",employeId);
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}


	public List<Employe> getAllEmployes() {
		log.debug("request to get all Employes:");
				return (List<Employe>) employeRepository.findAll();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		log.debug("request to affecter Contrat by id{} to Employe by id {}:",contratId,employeId);
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);

	}

}
