package fr.bibliotheque.forms;

public class PersonneForm {
	
	private Long idPersonne;
	
	private String nom;
	
	private String prenom;
	
	private String numeroAdherent;
	
	private boolean estResponsable;
	
	private Long idBibliotheque;

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

	public boolean isEstResponsable() {
		return estResponsable;
	}

	public void setEstResponsable(boolean estResponsable) {
		this.estResponsable = estResponsable;
	}

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public Long getIdBibliotheque() {
		return idBibliotheque;
	}

	public void setIdBibliotheque(Long idBibliotheque) {
		this.idBibliotheque = idBibliotheque;
	}
	
	
	

}
