package tn.esprit.spring.services;

import tn.esprit.spring.entities.*;

import java.util.Date;
import java.util.List;


public interface IContratService {

	public int ajouterContrat(Contrat contrat);
	public void deleteContratById(int contratId);
	public void deleteAllContratJPQL();
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, 
	Date dateDebut, Date dateFin);
	public String getEmployePrenomById(int employeId);
	public void deleteEmployeById(int employeId);
	
	
	

	
}
