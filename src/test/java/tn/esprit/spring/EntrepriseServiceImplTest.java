package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.IEntrepriseService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntrepriseServiceImplTest {

    @Autowired
    IEntrepriseService entrepriseService;

    Integer a,b;

    @Test
    public void testAjouterEntreprise(){

        Entreprise ent=new Entreprise("sofrecom","lac1");
        a= entrepriseService.ajouterEntreprise(ent);
        assertNotNull(a);


    }

    @Test
    public void testGetEntrepriseById(){

        Entreprise ent= entrepriseService.getEntrepriseById(13);

        assertNotNull(ent);

    }

    @Test
    public void testAjouterDepartement(){

        Departement dep=new Departement("ooredoo");
        b=entrepriseService.ajouterDepartement(dep);
        assertNotNull(b);
    }


    @Test
    public void testGetAllDepartementsNamesByEntreprise(){

        List<String> depNames= entrepriseService.getAllDepartementsNamesByEntreprise(15);
        assertNotNull(depNames);
    }

    @Test
    public void testAffecterDepartementAEntreprise(){

        entrepriseService.affecterDepartementAEntreprise(22,15);

    }

/*    @Test
    public void testDeleteDepartementById(){
        entrepriseService.deleteDepartementById(13);
    }

    @Test
    public void testDeleteEntrepriseById(){
        entrepriseService.deleteEntrepriseById(12);
    }*/





}
