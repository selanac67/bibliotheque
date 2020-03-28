package fr.bibliotheque.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Personne {
	
	@Id
	@GeneratedValue
	private Long idPersonne;
	
	private String numeroAdherent;
	
	private String nom;
	
	private String prenom;
	
	@OneToMany(mappedBy="personne")
	private List<Emprunt> emprunts=new ArrayList<>();
	
	@OneToOne
	private Bibliotheque bibliotheque;
	
	private boolean estResponsable;

	public Long getIdPersonne() {
		return idPersonne;
	}

	public boolean isEstResponsable() {
		return estResponsable;
	}

	public void setEstResponsable(boolean estResponsable) {
		this.estResponsable = estResponsable;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	

	public String getNumeroAdherent() {
		return numeroAdherent;
	}

	public void setNumeroAdherent(String numeroAdherent) {
		this.numeroAdherent = numeroAdherent;
	}

	public Bibliotheque getBibliotheque() {
		return bibliotheque;
	}

	public void setBibliotheque(Bibliotheque bibliotheque) {
		this.bibliotheque = bibliotheque;
	}
	
	

	public List<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(List<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bibliotheque == null) ? 0 : bibliotheque.hashCode());
		result = prime * result + (estResponsable ? 1231 : 1237);
		result = prime * result + ((idPersonne == null) ? 0 : idPersonne.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((numeroAdherent == null) ? 0 : numeroAdherent.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Personne))
			return false;
		Personne other = (Personne) obj;
		if (bibliotheque == null) {
			if (other.bibliotheque != null)
				return false;
		} else if (!bibliotheque.equals(other.bibliotheque))
			return false;
		if (estResponsable != other.estResponsable)
			return false;
		if (idPersonne == null) {
			if (other.idPersonne != null)
				return false;
		} else if (!idPersonne.equals(other.idPersonne))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numeroAdherent == null) {
			if (other.numeroAdherent != null)
				return false;
		} else if (!numeroAdherent.equals(other.numeroAdherent))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}


	

}
