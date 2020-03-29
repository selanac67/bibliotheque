package fr.bibliotheque.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Emprunt {
	
	@Id
	@GeneratedValue
	private Long idEmprunt;
	
	@ManyToOne
	private ProduitCulturel produitCulturel;
	
	@ManyToOne
	private Personne personne;
	
	private Date dateDebutEmprunt;
	
	private Date dateFinEmprunt;
	

	public Long getIdEmprunt() {
		return idEmprunt;
	}

	public void setIdEmprunt(Long idEmprunt) {
		this.idEmprunt = idEmprunt;
	}

	public ProduitCulturel getProduitCulturel() {
		return produitCulturel;
	}

	public void setProduitCulturel(ProduitCulturel produitCulturel) {
		this.produitCulturel = produitCulturel;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Date getDateDebutEmprunt() {
		return dateDebutEmprunt;
	}

	public void setDateDebutEmprunt(Date dateDebutEmprunt) {
		this.dateDebutEmprunt = dateDebutEmprunt;
	}

	public Date getDateFinEmprunt() {
		return dateFinEmprunt;
	}

	public void setDateFinEmprunt(Date dateFinEmprunt) {
		this.dateFinEmprunt = dateFinEmprunt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebutEmprunt == null) ? 0 : dateDebutEmprunt.hashCode());
		result = prime * result + ((dateFinEmprunt == null) ? 0 : dateFinEmprunt.hashCode());
		result = prime * result + ((idEmprunt == null) ? 0 : idEmprunt.hashCode());
		result = prime * result + ((personne == null) ? 0 : personne.hashCode());
		result = prime * result + ((produitCulturel == null) ? 0 : produitCulturel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Emprunt))
			return false;
		Emprunt other = (Emprunt) obj;
		if (dateDebutEmprunt == null) {
			if (other.dateDebutEmprunt != null)
				return false;
		} else if (!dateDebutEmprunt.equals(other.dateDebutEmprunt))
			return false;
		if (dateFinEmprunt == null) {
			if (other.dateFinEmprunt != null)
				return false;
		} else if (!dateFinEmprunt.equals(other.dateFinEmprunt))
			return false;
		if (idEmprunt == null) {
			if (other.idEmprunt != null)
				return false;
		} else if (!idEmprunt.equals(other.idEmprunt))
			return false;
		if (personne == null) {
			if (other.personne != null)
				return false;
		} else if (!personne.equals(other.personne))
			return false;
		if (produitCulturel == null) {
			if (other.produitCulturel != null)
				return false;
		} else if (!produitCulturel.equals(other.produitCulturel))
			return false;
		return true;
	}

	
	

	
	
	
}
