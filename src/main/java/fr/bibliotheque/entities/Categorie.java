package fr.bibliotheque.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Categorie {
	
	
	@Id
	@GeneratedValue
	private Long idCategorie;
	
	private String libelleCategorie;

	public Long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getLibelleCategorie() {
		return libelleCategorie;
	}

	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCategorie == null) ? 0 : idCategorie.hashCode());
		result = prime * result + ((libelleCategorie == null) ? 0 : libelleCategorie.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Categorie))
			return false;
		Categorie other = (Categorie) obj;
		if (idCategorie == null) {
			if (other.idCategorie != null)
				return false;
		} else if (!idCategorie.equals(other.idCategorie))
			return false;
		if (libelleCategorie == null) {
			if (other.libelleCategorie != null)
				return false;
		} else if (!libelleCategorie.equals(other.libelleCategorie))
			return false;
		return true;
	}
	
	

}
