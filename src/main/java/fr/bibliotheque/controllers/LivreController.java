package fr.bibliotheque.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.bibliotheque.entities.EProduitType;
import fr.bibliotheque.entities.Livre;
import fr.bibliotheque.forms.ProduitCulturelForm;
import fr.bibliotheque.services.ProduitCulturelService;


public class LivreController extends ProduitCulturelController {

	@Autowired
	ProduitCulturelService<Livre> livreService;
	
	
	

	@GetMapping("/editLivre")
	public String editLivre(@RequestParam(name = "idLivre", required = false) Long idLivre, Model model) {

		if (idLivre == null) {
			model.addAttribute("livreForm", new ProduitCulturelForm());
			model.addAttribute("newLivre", true);

		} else {

			Livre livre = (Livre) livreService.getProduitCulturel(idLivre).get();

			ProduitCulturelForm livreForm = new ProduitCulturelForm();
			livreForm.setIdProduitForm(idLivre);
			livreForm.setIdCategorie(livre.getCategorie().getIdCategorie());
			livreForm.setTitre(livre.getTitre());
			livreForm.setNomPhoto(livre.getNomPhoto());
			livreForm.setNote(livre.getNote());

			model.addAttribute("livreForm", livreForm);
			model.addAttribute("newLivre", false);

		}

		model.addAttribute("categories", categorieService.getListeCategories());


		return "produits-culturels/edit-produits-culturels";
	}

	/*
	 * @PostMapping("/sauverLivre") public String sauverLivre(Model
	 * model, @ModelAttribute("livreForm") ProduitCulturelForm livreForm) throws
	 * IllegalStateException, IOException {
	 * 
	 * Livre livre = new Livre();
	 * livre.setCategorie(categorieService.getCategorie(livreForm.getIdCategorie()))
	 * ; livre.setTitre(livreForm.getTitre()); livre.setNote(livreForm.getNote());
	 * 
	 * if (livreForm.getIdProduitForm() == null) {
	 * livreService.enregistrerNouveauProduit(livre, livreForm.getInputImage()); }
	 * else {
	 * livre.setDateAjout(livreService.getProduit(livreForm.getIdProduitForm()).
	 * getDateAjout()); livre.setIdProduit(livreForm.getIdProduitForm());
	 * livre.setNomPhoto(livreForm.getNomPhoto());
	 * livreService.majProduitCulturel(livre); }
	 * 
	 * // Refresh de la liste des livres model.addAttribute("produitsCulturelsDTO",
	 * livreService.getProduitCulturelDTO(EProduitType.L.getDiscriminatorValue()));
	 * 
	 * 
	 * return "produits-culturels/liste-produits-culturels"; }
	 */
	@GetMapping("/supprimerLivre")
	public String supprimerLivre(@RequestParam(name = "idLivre", required = true) Long idLivre, Model model) {

		Livre livreASupprimer = livreService.getProduit(idLivre);

		// Vérification que le livre n'est pas emprunté
		if (empruntService.isEmpruntEnCours(livreASupprimer)) {
			model.addAttribute("infoMessage", "Suppression impossible : le livre est en cours d'emprunt...");
		} else {
			livreService.supprimerProduitCulturel(livreASupprimer);
		}

		//refresh liste livres
		model.addAttribute("produitsCulturelsDTO", livreService.getProduitCulturelDTO(EProduitType.L.getDiscriminatorValue()));
		model.addAttribute("infoMessage", "Suppression du livre "+ livreASupprimer.getTitre()+" réalisée !");

		
		return "produits-culturels/liste-produits-culturels";
	}


}
