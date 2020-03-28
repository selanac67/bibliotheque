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
	private Livre livre;
	
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

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
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
		result = prime * result + ((livre == null) ? 0 : livre.hashCode());
		result = prime * result + ((personne == null) ? 0 : personne.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
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
		if (livre == null) {
			if (other.livre != null)
				return false;
		} else if (!livre.equals(other.livre))
			return false;
		if (personne == null) {
			if (other.personne != null)
				return false;
		} else if (!personne.equals(other.personne))
			return false;
		return true;
	}
	

	
	
	
}
