package fr.bibliotheque.forms;

import javax.validation.constraints.NotEmpty;

public class CategorieForm {
	
	@NotEmpty
	private String libelleCategorie;
	
	private Long idCategorie;

	public String getLibelleCategorie() {
		return libelleCategorie;
	}

	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}

	public Long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}

	

}
