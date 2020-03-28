package fr.bibliotheque.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.bibliotheque.entities.Livre;
import fr.bibliotheque.forms.LivreForm;
import fr.bibliotheque.services.CategorieService;
import fr.bibliotheque.services.EmpruntService;
import fr.bibliotheque.services.LivreService;

@Controller
public class LivreController  extends BibliothequeController {

	@Autowired
	LivreService livreService;
		
	@Autowired
	CategorieService categorieService;
	
	@Autowired
	EmpruntService empruntService;


	
	@GetMapping("/listeLivresDTO")
    public String listerLivresDTO(Model model) {
      		
		model.addAttribute("livresDTO", livreService.getLivresDTO());
	
		return "livres/liste-livres";        
    }
	
	@GetMapping("/editLivre")
    public String editLivre(
    		@RequestParam(name="idLivre", required=false) Long idLivre,
    		Model model) {
       
		if(idLivre==null){
			model.addAttribute("livreForm", new LivreForm());
			model.addAttribute("newLivre", true);
		
		}else{		
			
			Livre livre= livreService.getLivre(idLivre);
			
			LivreForm livreForm = new LivreForm();
			livreForm.setIdLivreForm(idLivre);
			livreForm.setIdCategorie(livre.getCategorie().getIdCategorie());
			livreForm.setTitre(livre.getTitre());
			livreForm.setNomPhoto(livre.getNomPhoto());
			livreForm.setNote(livre.getNote());
			
			model.addAttribute("livreForm",livreForm);
			model.addAttribute("newLivre", false);

		}
		
			model.addAttribute("categories",categorieService.getListeCategories());
			
		
		return "livres/edit-livre";
    }
	
	
	@PostMapping("/sauverLivre")
    public String sauverLivre(Model model,
    		@ModelAttribute("livreForm") LivreForm livreForm
    		) throws IllegalStateException, IOException {
		

		Livre livre = new Livre();
		livre.setCategorie(categorieService.getCategorie(livreForm.getIdCategorie()));
		livre.setTitre(livreForm.getTitre());
		livre.setNote(livreForm.getNote());

		
		if(livreForm.getIdLivreForm()==null){
			
			livreService.enregistrerNouveauLivre(livre,livreForm.getInputImage());

		}else{
			
			livre.setDateAjout(livreService.getLivre(livreForm.getIdLivreForm()).getDateAjout());
			livre.setIdLivre(livreForm.getIdLivreForm());
			livre.setNomPhoto(livreForm.getNomPhoto());
			livreService.majLivre(livre);
			
		}
				
		//Refresh de la liste des livres
		model.addAttribute("livresDTO", livreService.getLivresDTO());
		
		return "livres/liste-livres";
    }
	
	

	@GetMapping("/supprimerLivre")
    public String supprimerLivre(@RequestParam(name="idLivre", required=true) Long idLivre,
    								Model model) {
		
		Livre livreASupprimer= livreService.getLivre(idLivre);
		
		//Vérification que le livre n'est pas emprunté
		if(empruntService.isEmpruntEnCours(livreASupprimer)){
			model.addAttribute("infoMessage", "Suppression impossible : le livre est en cours d'emprunt...");
		}else{
			livreService.supprimerLivre(livreASupprimer);
		}
		
		model.addAttribute("livresDTO", livreService.getLivresDTO());

	
		return "livres/liste-livres";        
    }
	
	
	
	
}
