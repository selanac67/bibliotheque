package fr.bibliotheque.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("J")
public class Jeu extends ProduitCulturel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7950854326380744302L;

}
