package fr.bibliotheque.dao;

import org.springframework.stereotype.Repository;

import fr.bibliotheque.entities.Livre;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface LivreDAO extends CrudRepository<Livre, Long> {
	
		  
	}



