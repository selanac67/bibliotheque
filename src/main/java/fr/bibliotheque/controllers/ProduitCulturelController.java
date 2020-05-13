package fr.bibliotheque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.bibliotheque.entities.EProduitType;
import fr.bibliotheque.entities.Jeu;
import fr.bibliotheque.entities.Livre;
import fr.bibliotheque.entities.ProduitCulturel;
import fr.bibliotheque.forms.ProduitCulturelForm;
import fr.bibliotheque.services.CategorieService;
import fr.bibliotheque.services.EmpruntService;
import fr.bibliotheque.services.ProduitCulturelService;

@Controller
public  class ProduitCulturelController extends BibliothequeController {

	@Autowired
	CategorieService categorieService;

	@Autowired
	EmpruntService empruntService;

	@Autowired
	ProduitCulturelService produitCulturelService;
	
	
	/**
	 * Ecrans de listes
	 * @param model
	 * @param discriminatorValue
	 * @return
	 */
	@GetMapping("/listerProduitsDTO")
	public String listerProduitsDTO(Model model, @RequestParam(name = "produitType", required = true) String discriminatorValue) {

		model.addAttribute("produitsCulturelsDTO", produitCulturelService.getProduitCulturelDTO(discriminatorValue));
		enrichissementModel(model,discriminatorValue);

		return "produits-culturels/liste-produits-culturels";
	}

	
	/*
	 * Ouverture du formulaire d édition
	 */
	@GetMapping("/editerProduit")
	public String editerProduit(Model model, @RequestParam(name = "idProduit", required = false) Long idProduit,
			@RequestParam(name = "produitType", required = false) String produitTypeDiscriminator) {

		
		ProduitCulturelForm produitForm = new ProduitCulturelForm();
		
		if (idProduit == null) {
			produitForm.setProduitType(produitTypeDiscriminator);
			model.addAttribute("newProduit", true);
		} else {
			
			ProduitCulturel produit = produitCulturelService.getProduit(idProduit);
			produitForm.setIdProduitForm(idProduit);
			produitForm.setIdCategorie(produit.getCategorie().getIdCategorie());
			produitForm.setTitre(produit.getTitre());
			produitForm.setNomPhoto(produit.getNomPhoto());
			produitForm.setNote(produit.getNote());
			produitForm.setProduitType(produit.getProduitType());

			model.addAttribute("newProduit", false);

		}
		
		model.addAttribute("produitForm", produitForm);

		model.addAttribute("categories", categorieService.getListeCategories());

		enrichissementModel(model,produitForm.getProduitType());

		return "produits-culturels/edit-produits-culturels";
	}

	/**
	 * SAuvegarde en base du formulaire d'édition
	 * @param model
	 * @param produitForm
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/sauverProduit")
	public String sauverProduit(Model model,
			@ModelAttribute("produitForm") ProduitCulturelForm produitForm)
			throws Exception {

		ProduitCulturel produitASauver;

		String produitType =produitForm.getProduitType();
		
		if (produitType.equals(EProduitType.J.getDiscriminatorValue())) {
			produitASauver = new Jeu();
		} else if (produitType.equals(EProduitType.L.L.getDiscriminatorValue())) {
			produitASauver = new Livre();

		} else {
			throw new Exception("Type de produit inconnu");
		}

		produitASauver.setCategorie(categorieService.getCategorie(produitForm.getIdCategorie()));
		produitASauver.setTitre(produitForm.getTitre());
		produitASauver.setNote(produitForm.getNote());

		if (produitForm.getIdProduitForm() == null) {
			produitCulturelService.enregistrerNouveauProduit(produitASauver, produitForm.getInputImage());
		} else {
			produitASauver
					.setDateAjout(produitCulturelService.getProduit(produitForm.getIdProduitForm()).getDateAjout());
			produitASauver.setIdProduit(produitForm.getIdProduitForm());
			produitASauver.setNomPhoto(produitForm.getNomPhoto());
			produitCulturelService.majProduitCulturel(produitASauver);
		}

		enrichissementModel(model,produitType);
		// Refresh de la liste des produits
		model.addAttribute("produitsCulturelsDTO",
				produitCulturelService.getProduitCulturelDTO(produitType));

		return "produits-culturels/liste-produits-culturels";
	}
	
	@GetMapping("/supprimerProduit")
	public String supprimerProduit(@RequestParam(name = "idProduit", required = true) Long idProduit, Model model) {

		ProduitCulturel pcASupprimer = produitCulturelService.getProduit(idProduit);
		String infoMessage = new String();

		// Vérification que le livre n'est pas emprunté
		if (empruntService.isEmpruntEnCours(pcASupprimer)) {
			infoMessage= "Suppression impossible : l'objet est en cours d'emprunt...";
		} else {
			
			try {
				produitCulturelService.supprimerProduitCulturel(pcASupprimer);
			} catch (Exception e) {
				infoMessage= "Suppression impossible : il se peut que l'objet ait déjà été emprunté au moins une fois...";
			}
		}

		if(infoMessage.length()==0) {
			infoMessage="Suppression du livre "+ pcASupprimer.getTitre()+" réalisée !";
		}
		
		model.addAttribute("infoMessage",infoMessage );

		
		//refresh liste produit culturel
		model.addAttribute("produitsCulturelsDTO", produitCulturelService.getProduitCulturelDTO(EProduitType.L.getDiscriminatorValue()));

		enrichissementModel(model,pcASupprimer.getProduitType());
	
		return "produits-culturels/liste-produits-culturels";
	}	
	
	
	
	

	public void enrichissementModel(Model model, String produitTypeDiscriminator) {
		
		
		EProduitType eTypeProduit = EProduitType.valueOf(produitTypeDiscriminator);
		
		model.addAttribute("produitTypeDiscriminator", eTypeProduit.getDiscriminatorValue());
		model.addAttribute("produitTypePluriel", eTypeProduit.getNomCourtPluriel());
		model.addAttribute("produitTypeSingulier", eTypeProduit.getNomCourtSingulier());
	}

}
