package fr.bibliotheque.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bibliotheque.dao.PersonneDAO;
import fr.bibliotheque.entities.Personne;

@Service
public class PersonneService {

	
	@Autowired
	PersonneDAO personneDAO;
	
	
	public Personne getPersonne(Long id){
		Optional<Personne> optPersonne= personneDAO.findById(id);
		return optPersonne.isPresent()?optPersonne.get():new Personne() ;
	}
	
	public Personne getPersonneByNumeroAdherent(String numeroAdherent){
		
		return personneDAO.findByNumeroAdherent(numeroAdherent);
	}
	
	
	public List<Personne> getListePersonnes(){
		
		List<Personne> personnes = new ArrayList<>();
		
		Iterable<Personne> iterPersonne= personneDAO.findAll();
		iterPersonne.forEach(personnes::add);		
		
		return personnes;
	}
	
	
	public void enregistrerPersonne(Personne personne){	
		personneDAO.save(personne);
	}
	
}
