package fr.bibliotheque.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bibliotheque.dao.EmpruntDAO;
import fr.bibliotheque.entities.Emprunt;
import fr.bibliotheque.entities.Livre;
import fr.bibliotheque.entities.Personne;

@Service
public class EmpruntService {

	@Autowired
	EmpruntDAO empruntDAO;
	
	/**
	 * Liste tous les emprunts (terminés ou non)
	 */
	public List<Emprunt> getEmprunts(){
		
		List<Emprunt> emprunts = new ArrayList<>();
	
		Iterable<Emprunt> iterLivre= empruntDAO.findAll();
		iterLivre.forEach(emprunts::add);		
		
		return emprunts;		
	}
	
	/**
	 * Ne liste que les emprunts en cours
	 * @return
	 */
	public List<Emprunt> getEmpruntsEncours(){
		
		List<Emprunt> emprunts = new ArrayList<>();
	
		Iterable<Emprunt> iterLivre= empruntDAO.findByDateFinEmpruntIsNull();
		iterLivre.forEach(emprunts::add);		
		
		return emprunts;		
	}
	
	public List<Emprunt> getEmpruntsTermines(){
		
		List<Emprunt> emprunts = new ArrayList<>();
		
		Iterable<Emprunt> iterLivre= empruntDAO.findByDateFinEmpruntIsNotNull();
		iterLivre.forEach(emprunts::add);	
		
		return emprunts;
	}
	
	
	public Emprunt getEmprunt(Long idEmprunt){
		Optional<Emprunt> emprunt=empruntDAO.findById(idEmprunt);
		return emprunt.isPresent()?emprunt.get():new Emprunt() ;
	}
	
	public List<Emprunt> getEmprunts(Personne personne) {
		
		List<Emprunt> emprunts=empruntDAO.findByPersonne(personne);

		return emprunts;
	}
	
	public List<Emprunt> getEmpruntsEnCours(Personne personne) {
		
		List<Emprunt> emprunts=empruntDAO.findByPersonneAndDateFinEmpruntIsNull(personne);

		return emprunts;
	}
	
	public Object getEmpruntsTermines(Personne personneSession) {

		List<Emprunt> emprunts=empruntDAO.findByPersonneAndDateFinEmpruntIsNotNull(personneSession);

		return emprunts;
		
	}
	

	
	
	public void enregistrerEmprunt(Emprunt emprunt){	
		
		empruntDAO.save(emprunt);
	}
	
	public boolean isEmpruntEnCours(Livre livre){
		
		boolean kEmpruntEnCours=false;
		
		List<Emprunt> lstEmprunt=
		empruntDAO.findByLivreAndDateFinEmpruntIsNull(livre);
		
		if(lstEmprunt.size()>0){
			kEmpruntEnCours=true;
		}
		
		return kEmpruntEnCours;

	}


	
	

	
}
