package fr.bibliotheque.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.bibliotheque.entities.Categorie;


@Repository
public interface CategorieDAO extends CrudRepository<Categorie, Long> {
		  
	}