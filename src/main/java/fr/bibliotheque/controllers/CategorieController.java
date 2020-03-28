package fr.bibliotheque.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.bibliotheque.entities.Categorie;
import fr.bibliotheque.forms.CategorieForm;
import fr.bibliotheque.services.CategorieService;

@Controller
public class CategorieController extends BibliothequeController {
	
	@Autowired
	CategorieService categorieService;
	

	@GetMapping("/listeCategories")
    public String listerCategories(Model model) {
      		
		model.addAttribute("categories", categorieService.getListeCategories());
	
		return "categories/liste-categories";        
    }
	
	@GetMapping("/editCategorie")
    public String editCategorie(
    		@RequestParam(name="idCategorie", required=false) Long idCategorie,
    		Model model) {
		
		CategorieForm categorieForm = new CategorieForm();
		boolean newCategorie = false;
       
		if(idCategorie==null){
			newCategorie=true;
		}else{
			Categorie categorieAModifier= categorieService.getCategorie(idCategorie);
			categorieForm.setIdCategorie(idCategorie);
			categorieForm.setLibelleCategorie(categorieAModifier.getLibelleCategorie());
		}
		
		model.addAttribute("categorieForm",categorieForm);
		model.addAttribute("newCategorie",newCategorie);

		
		return "categories/edit-categorie";
    }
	
	
	@PostMapping("/sauverCategorie")
    public String sauverCategorie(Model model,
    		@ModelAttribute("categorieForm") CategorieForm categorieForm
    		) throws IllegalStateException, IOException {
		
		Categorie categorie=new Categorie();
		
		if(categorieForm.getIdCategorie()!=null){		
			categorie.setIdCategorie(categorieForm.getIdCategorie());
		}
		
		categorie.setLibelleCategorie(categorieForm.getLibelleCategorie());
		categorieService.enregistrerCategorie(categorie);
		
		//Refresh de la liste des categories
		model.addAttribute("categories",categorieService.getListeCategories());
		
		return "categories/liste-categories";
    }
	
	
	@GetMapping("/supprimerCategorie")
	public String supprimerCategorie(
    		@RequestParam(name="idCategorie", required=true) Long idCategorie,
    		Model model) {
		
		Categorie categorieASupprimer = categorieService.getCategorie(idCategorie);
		
		categorieService.supprimerCategorie(categorieASupprimer);
		
		//Refresh de la liste des categories
		model.addAttribute("categories",categorieService.getListeCategories());
					
			return "categories/liste-categories";
    }
 
	
	
	
	
}
