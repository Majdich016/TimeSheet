package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.services.*;


@Controller
public class IControllerEmployeImpl  {
	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;
	@Autowired
	IContratService iContratService;
	@Autowired
	IDepartementService iDepartementService;

	
	
	public int ajouterEmploye(Employe employe)
	{
		iemployeservice.ajouterEmploye(employe);
		

		
		return employe.getId();
	}
    
	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		iemployeservice.mettreAjourEmailByEmployeId(email, employeId);

	}

	public void affecterEmployeADepartement(int employeId, int depId) {
		iDepartementService.affecterEmployeADepartement(employeId, depId);
		
	}


	
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		iDepartementService.desaffecterEmployeDuDepartement(employeId, depId);
	}

	
	public int ajouterContrat(Contrat contrat) {
		iContratService.ajouterContrat(contrat);
		return contrat.getReference();
	}
	
	public void affecterContratAEmploye(int contratId, int employeId)
	{
		iemployeservice.affecterContratAEmploye(contratId, employeId);
	}

	
	public String getEmployePrenomById(int employeId) {
		return iContratService.getEmployePrenomById(employeId);
	}

	
	public void deleteEmployeById(int employeId) {
		iContratService.deleteEmployeById(employeId);
		
	}
	public void deleteContratById(int contratId) {
		iContratService.deleteContratById(contratId);
	}

	
	public int getNombreEmployeJPQL() {
		
		return iemployeservice.getNombreEmployeJPQL();
	}

	
	public List<String> getAllEmployeNamesJPQL() {
		
		return iemployeservice.getAllEmployeNamesJPQL();
	}

	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return iDepartementService.getAllEmployeByEntreprise(entreprise);
	}


	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {	
	iDepartementService.mettreAjourEmailByEmployeIdJPQL(email, employeId);
		
	}


	public void deleteAllContratJPQL() {
		iContratService.deleteAllContratJPQL();
		
	}

	
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return iContratService.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}




	public List<Employe> getAllEmployes() {
		
		return iemployeservice.getAllEmployes();
	}

	
	

	
	

	
	

	
	
    
	
	
	
}
