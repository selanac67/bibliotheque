package fr.bibliotheque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.bibliotheque.entities.EProduitType;
import fr.bibliotheque.entities.Jeu;
import fr.bibliotheque.entities.ProduitCulturel;
import fr.bibliotheque.forms.ProduitCulturelForm;
import fr.bibliotheque.services.ProduitCulturelService;

public class JeuController extends ProduitCulturelController {
	
	
	@Autowired
	ProduitCulturelService<Jeu> jeuService;

	
	@GetMapping("/listeJeuxDTO")
	public String listerJeuxDTO(Model model) {

		model.addAttribute("produitsCulturelsDTO", jeuService.getProduitCulturelDTO(EProduitType.J.getDiscriminatorValue()));
		enrichissementModel(model);
		
		return "produits-culturels/liste-produits-culturels";
	}
	
	 void enrichissementModel(Model model) {
		model.addAttribute("produitTypeDiscriminator", EProduitType.J.getDiscriminatorValue());
		model.addAttribute("produitTypePluriel", EProduitType.J.getNomCourtPluriel());
		model.addAttribute("produitTypeSingulier", EProduitType.J.getNomCourtSingulier());
	}

	 
	 @GetMapping("/editJeu")
		public String editJeu(@RequestParam(name = "idProduit", required = false) Long idProduit, Model model) {

			if (idProduit == null) {
				model.addAttribute("produitForm", new ProduitCulturelForm());
				model.addAttribute("newProduit", true);

			} else {

				ProduitCulturel produit = jeuService.getProduitCulturel(idProduit).get();

				ProduitCulturelForm produitForm = new ProduitCulturelForm();
				produitForm.setIdProduitForm(idProduit);
				produitForm.setIdCategorie(produit.getCategorie().getIdCategorie());
				produitForm.setTitre(produit.getTitre());
				produitForm.setNomPhoto(produit.getNomPhoto());
				produitForm.setNote(produit.getNote());

				model.addAttribute("produitForm", produitForm);
				model.addAttribute("newProduit", false);

			}

			model.addAttribute("categories", categorieService.getListeCategories());	
			enrichissementModel(model);


			return "produits-culturels/edit-produits-culturels";
		} 
	 
}
