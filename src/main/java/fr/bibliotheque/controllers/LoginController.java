package fr.bibliotheque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import fr.bibliotheque.entities.Personne;
import fr.bibliotheque.services.PersonneService;

@Controller
public class LoginController  extends BibliothequeController {
	
	@Autowired
	PersonneService personneService;

	@GetMapping("/login")
    public String login(@RequestParam(name="numeroAdherent", required=true) String numeroAdherent, Model model) {
       		
		Personne personne = personneService.getPersonneByNumeroAdherent(numeroAdherent);
		
		if(personne==null ){
			
			model.addAttribute("infoMessage", "Il n'existe pas d'utilisateur ayant comme numéro d'adhérent "+numeroAdherent);
			return "index";
		
		}else{
			
			model.addAttribute("personneSession", personne);
			model.addAttribute("bibliothequeSession", personne.getBibliotheque());

			if(personne.isEstResponsable()){
				return "home/index-responsable";
			}else{
				return "home/index-membre";

			}
			
		}  
    }
	
	@GetMapping("/home")
	public String home(@SessionAttribute("personneSession") Personne personneSession,Model model){
		
		if(personneSession!=null){
			if(personneSession.isEstResponsable()){
				return "home/index-responsable";
			}else{
				return "home/index-membre";
			}
		}
		
		
		return "redirect:/";
		
	}
	
}
