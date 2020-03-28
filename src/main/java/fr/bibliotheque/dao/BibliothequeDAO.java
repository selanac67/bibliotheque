package fr.bibliotheque.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.bibliotheque.entities.Bibliotheque;


@Repository
public interface BibliothequeDAO extends CrudRepository<Bibliotheque, Long> {
		  
	}