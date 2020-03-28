package fr.bibliotheque.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.bibliotheque.entities.Personne;

@Repository
public interface PersonneDAO extends CrudRepository<Personne, Long> {
		  
	Personne findByNumeroAdherent(String numeroAdherent);

	}



