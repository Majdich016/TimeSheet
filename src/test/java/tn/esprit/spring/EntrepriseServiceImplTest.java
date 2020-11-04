package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.EntrepriseServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntrepriseServiceImplTest {

    @Autowired
    EntrepriseServiceImpl entrepriseService;

    @Test
    public void testAjouterEntreprise(){
        Entreprise ent=new Entreprise();
        ent.setId(4);
        ent.setName("Sopra");
        ent.setRaisonSocial("Lac2");

        assertEquals(4,entrepriseService.ajouterEntreprise(ent));
    }

    @Test
    public void testAjouterDepartement(){
        Departement dep=new Departement();
        dep.setId(3);
        dep.setName("ooredoo");

        assertEquals(3,entrepriseService.ajouterDepartement(dep));
    }

    @Test
    public void testAffecterDepartementAEntreprise(){
        entrepriseService.affecterDepartementAEntreprise(3,4);

        assertEquals("ooredoo",entrepriseService.getAllDepartementsNamesByEntreprise(4).get(0));

    }

    @Test
    public void testGetAllDepartementsNamesByEntreprise(){

        List<String> depNames= entrepriseService.getAllDepartementsNamesByEntreprise(4);
        assertNotNull(depNames);
    }

    @Test
    public void testDeleteEntrepriseById(){
        entrepriseService.deleteEntrepriseById(4);
    }

    @Test
    public void testDeleteDepartementById(){
        entrepriseService.deleteDepartementById(3);
    }

    @Test
    public void testGetEntrepriseById(){
        Entreprise ent= entrepriseService.getEntrepriseById(4);

        assertNotNull(ent);

    }

}