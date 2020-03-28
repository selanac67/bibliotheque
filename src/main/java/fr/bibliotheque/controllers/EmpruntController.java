package fr.bibliotheque.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import fr.bibliotheque.entities.Emprunt;
import fr.bibliotheque.entities.Livre;
import fr.bibliotheque.entities.Personne;
import fr.bibliotheque.forms.EmpruntForm;
import fr.bibliotheque.services.EmpruntService;
import fr.bibliotheque.services.LivreService;
import fr.bibliotheque.services.PersonneService;

@Controller
public class EmpruntController extends BibliothequeController {

	@Autowired
	EmpruntService empruntService;

	@Autowired
	LivreService livreService;

	@Autowired
	PersonneService personneService;

	@GetMapping("/listerEmprunts")
	public String listerEmprunts(Model model) {

		model.addAttribute("empruntsEncours", empruntService.getEmpruntsEncours());
		model.addAttribute("empruntsTermines", empruntService.getEmpruntsTermines());

		return "emprunts/liste-emprunts";
	}

	@GetMapping("/listerMesEmprunts")
	public String listerEmpruntsPersonne(Model model, @SessionAttribute("personneSession") Personne personneSession) {

		model.addAttribute("empruntsEncours", empruntService.getEmpruntsEnCours(personneSession));
		model.addAttribute("empruntsTermines", empruntService.getEmpruntsTermines(personneSession));

		return "emprunts/liste-emprunts";
	}

	@GetMapping("/sauverEmprunt")
	public String sauverEmprunt(Model model, @RequestParam(name = "idLivre", required = true) Long idLivre,
			@RequestParam(name = "idPersonne", required = true) Long idPersonne) throws IllegalStateException {

		Livre livreAEmprunter = livreService.getLivre(idLivre);
		Personne personneEmprunteuse = personneService.getPersonne(idPersonne);

		Emprunt emprunt = new Emprunt();
		emprunt.setLivre(livreAEmprunter);
		emprunt.setPersonne(personneEmprunteuse);
		emprunt.setDateDebutEmprunt(new Date());

		empruntService.enregistrerEmprunt(emprunt);

		livreAEmprunter.addEmprunt(emprunt);
		livreService.majLivre(livreAEmprunter);

		model.addAttribute("empruntsEncours", empruntService.getEmpruntsEncours());
		model.addAttribute("empruntsTermines", empruntService.getEmpruntsTermines());

		return "emprunts/liste-emprunts";
	}

	// Fonction d'admin
	@GetMapping("/editEmprunt")
	public String editEmprunt(Model model,
			@RequestParam(name = "idLivre", required = true) Long idLivre) {

		EmpruntForm empruntForm = new EmpruntForm();

		// Cas de l'appel par un responsable		
		Livre livreAEmprunter = livreService.getLivre(idLivre);
		empruntForm.setIdLivre(idLivre);
		empruntForm.setTitre(livreAEmprunter.getTitre());
		model.addAttribute("empruntForm", empruntForm);

		List<Personne> personnes = personneService.getListePersonnes();
		model.addAttribute("personnes", personnes);

		return "emprunts/edit-emprunt";
	}

	// Fonction d'admin
	@GetMapping("/retournerLivre")
	public String retournerLivre(Model model, @RequestParam(name = "idEmprunt", required = true) Long idEmprunt,
			@SessionAttribute("personneSession") Personne personneSession) throws IllegalStateException {

		Emprunt empruntTermine = empruntService.getEmprunt(idEmprunt);
		empruntTermine.setDateFinEmprunt(new Date());
		empruntService.enregistrerEmprunt(empruntTermine);

		model.addAttribute("empruntsEncours", empruntService.getEmpruntsEncours());
		model.addAttribute("empruntsTermines", empruntService.getEmpruntsTermines());

		return "emprunts/liste-emprunts";
	}

}
