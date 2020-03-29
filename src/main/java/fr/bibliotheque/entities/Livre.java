package fr.bibliotheque.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("L")
public class Livre extends ProduitCulturel{

	private static final long serialVersionUID = -6005777793849549630L;

	
}
