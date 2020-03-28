package fr.bibliotheque.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bibliotheque {

	@Id
	@GeneratedValue
	private Long idBibliotheque;
	
	private String nomBibliotheque;
	
	public String getNomBibliotheque() {
		return nomBibliotheque;
	}

	public void setNomBibliotheque(String nomBibliotheque) {
		this.nomBibliotheque = nomBibliotheque;
	}

	public Long getIdBibliotheque() {
		return idBibliotheque;
	}

	public void setIdBibliotheque(Long idBibliotheque) {
		this.idBibliotheque = idBibliotheque;
	}
	
	

	
}
