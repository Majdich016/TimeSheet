package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
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

	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();

	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {

		Optional<Employe> value = employeRepository.findById(employeId);
		if (value.isPresent()) {
			Employe employe = value.get();
			employe.setEmail(email);
			System.out.println("test test");
			employeRepository.save(employe);
		}

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {

		Optional<Contrat> contratManagedEntity = contratRepoistory.findById(contratId);
		Optional<Employe> value = employeRepository.findById(employeId);
		if (contratManagedEntity.isPresent()) {
			Contrat contrat = contratManagedEntity.get();
			if (value.isPresent()) {
				Employe employe = value.get();
				contrat.setEmploye(employe);

			}
			contratRepoistory.save(contrat);

		}

	}

}
