package tn.esprit.spring.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class ContratServiceImpl implements IContratService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	private static final Logger l = Logger.getLogger(ContratServiceImpl.class);

	public int ajouterContrat(Contrat contrat) {
		try {

			l.info("je vais ajouter un contrat");

			l.debug("contrat." + contrat);

			contratRepoistory.save(contrat);
			l.debug("retour du save ." + contratRepoistory.save(contrat));
			return contrat.getReference();
		} catch (Exception e) {
			l.error("Erreur dans ajouterContrat" + e);
			return 0;
		}
	}

	public String getEmployePrenomById(int employeId) {

		try {

			l.info("getEmployePrenomById");

			Optional<Employe> value = employeRepository.findById(employeId);
			if (value.isPresent()) {
				Employe employeManagedEntity = value.get();
				l.debug("getEmployePrenomById." + employeManagedEntity);
				return employeManagedEntity.getPrenom();
			} else {
				return null;
			}

		} catch (Exception e) {
			l.error("Erreur dans getEmployePrenomById" + e);
			return null;
		}

	}

	public void deleteEmployeById(int employeId) {

		try {

			l.info("deleteEmployeById");

			l.debug("EmployeId." + employeId);
			Optional<Employe> value = employeRepository.findById(employeId);
			if (value.isPresent()) {
				Employe employe = value.get();

				l.debug("EmployeById." + employe);

				for (Departement dep : employe.getDepartements()) {
					dep.getEmployes().remove(employe);

				}

				employeRepository.delete(employe);
			}

		} catch (Exception e) {
			l.error("Erreur dans deleteEmployeById" + e);

		}

	}

	public void deleteContratById(int contratId) {

		try {

			l.info("deleteContratById");

			l.debug("contratId." + contratId);
			Optional<Contrat> value = contratRepoistory.findById(contratId);
			
			if(value.isPresent()){
			Contrat contratManagedEntity = value.get();
			
			l.debug("deleteContratById." + contratManagedEntity);
			contratRepoistory.delete(contratManagedEntity);}
		} catch (Exception e) {
			l.error("Erreur dans deleteContratById" + e);

		}
	}

	public void deleteAllContratJPQL() {

		try {

			l.info("deleteAllContratJPQL");

			l.debug("deleteAllContratJPQL.");

			employeRepository.deleteAllContratJPQL();
		} catch (Exception e) {
			l.error("deleteAllContratJPQL" + e);

		}

	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {

		try {

			l.info("getTimesheetsByMissionAndDate");

			l.debug("Employe." + employe);
			l.debug("Mission." + mission);
			l.debug("dateDebut." + dateDebut);
			l.debug("dateFin." + dateFin);
			return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
		} catch (

		Exception e) {
			l.error("getTimesheetsByMissionAndDate" + e);
			return Collections.emptyList();
		}

	}

}
