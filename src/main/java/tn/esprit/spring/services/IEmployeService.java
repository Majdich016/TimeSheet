package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;


public interface IEmployeService {
	
	public int ajouterEmploye(Employe employe);
	public int mettreAjourEmailByEmployeId(String email, int employeId);
	public int getNombreEmployeJPQL();
	public List<String> getAllEmployeNamesJPQL();
	public float getSalaireByEmployeIdJPQL(int employeId);
	public List<Employe> getAllEmployes();
	public int affecterContratAEmploye(int contratId, int employeId);

	
	
	

	
}
