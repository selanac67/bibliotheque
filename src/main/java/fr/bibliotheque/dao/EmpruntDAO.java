package fr.bibliotheque.dao;

import org.springframework.stereotype.Repository;

import fr.bibliotheque.entities.Emprunt;
import fr.bibliotheque.entities.ProduitCulturel;
import fr.bibliotheque.entities.Personne;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface EmpruntDAO extends CrudRepository<Emprunt, Long> {
		  
	List<Emprunt> findByPersonne(Personne personne);
	
	List<Emprunt> findByPersonneAndDateFinEmpruntIsNull(Personne personne);
	
	List<Emprunt> findByPersonneAndDateFinEmpruntIsNotNull(Personne personne);
	
	List<Emprunt> findByDateFinEmpruntIsNull();
	
	List<Emprunt> findByDateFinEmpruntIsNotNull();
	
	List<Emprunt> findByProduitCulturelAndDateFinEmpruntIsNull(ProduitCulturel produitCulturel);

	
	}



