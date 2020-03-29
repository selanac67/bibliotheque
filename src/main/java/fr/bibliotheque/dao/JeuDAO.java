package fr.bibliotheque.dao;

import org.springframework.stereotype.Repository;

import fr.bibliotheque.entities.Jeu;

@Repository
public interface JeuDAO extends ProduitCulturelDAO<Jeu> {}
