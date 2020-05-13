package fr.bibliotheque.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import fr.bibliotheque.entities.Bibliotheque;
import fr.bibliotheque.entities.Personne;
import fr.bibliotheque.forms.PersonneForm;
import fr.bibliotheque.services.PersonneService;

@Controller
public class PersonneController  extends BibliothequeController {
	
	@Autowired
	PersonneService personneService;
	
	@GetMapping("/listerPersonnes")
    public String listerPersonnes(Model model) {
      		
		model.addAttribute("personnes", personneService.getListePersonnes());
	
		return "personnes/liste-personnes";        
    }
	
	@GetMapping("/editerPersonne")
    public String editPersonne(
    		@RequestParam(name="idPersonne", required=false) Long idPersonne,
    		Model model) {
       
		if(idPersonne==null){
			model.addAttribute("personneForm", new PersonneForm());
			model.addAttribute("newPersonne", true);

		}else{		
			model.addAttribute("personneForm",personneService.getPersonne(idPersonne));
			model.addAttribute("newPersonne", false);

		}
			
		return "personnes/edit-personne";
    }
	
	
	@PostMapping("/sauverPersonne")
    public String sauverPersonne(Model model,
    		@ModelAttribute("personneForm") PersonneForm personneForm,
    		@SessionAttribute("bibliothequeSession") Bibliotheque bibliotheque
    		) throws IllegalStateException, IOException {
		
		Personne personne=new Personne();
		
		if(personneForm.getIdPersonne()!=null){
			personne = personneService.getPersonne(personneForm.getIdPersonne());
		}
			
			personne.setNom(personneForm.getNom());
			personne.setPrenom(personneForm.getPrenom());
			personne.setNumeroAdherent(personneForm.getNumeroAdherent());
			personne.setEstResponsable(personneForm.isEstResponsable());
			personne.setBibliotheque(bibliotheque);
			
			personneService.enregistrerPersonne(personne);
		
		
		//Refresh de la liste des personnes
		model.addAttribute("personnes",personneService.getListePersonnes());
		
		return "personnes/liste-personnes";
    }
	
	

 
	
	
	
	
}
