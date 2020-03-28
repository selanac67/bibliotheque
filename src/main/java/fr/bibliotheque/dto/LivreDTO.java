package fr.bibliotheque.dto;

import java.util.Date;
import java.util.List;

import fr.bibliotheque.entities.Categorie;
import fr.bibliotheque.entities.Emprunt;
import fr.bibliotheque.entities.Livre;

public class LivreDTO {
	

	private Long idLivre;
	private String titre;
	private Integer note;
	private Categorie categorie;
	private boolean kEmprunt = false;
	private String nomPhoto;
	private Date dateAjout;
	
	
	 public LivreDTO(Livre livre){	
		this.titre=livre.getTitre();
		this.idLivre=livre.getIdLivre();
		this.categorie=livre.getCategorie();
		this.nomPhoto=livre.getNomPhoto();
		this.note=livre.getNote();
		this.dateAjout=livre.getDateAjout();
		
		List<Emprunt> emprunts = livre.getEmprunts();
		
		for(Emprunt emprunt : emprunts){		
			if(emprunt.getDateFinEmprunt()==null){
				kEmprunt=true;
				break;
			}
		}	
	 }
	
	public Long getIdLivre() {
		return idLivre;
	}


	public void setIdLivre(Long idLivre) {
		this.idLivre = idLivre;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public boolean iskEmprunt() {
		return kEmprunt;
	}


	public void setkEmprunt(boolean kEmprunt) {
		this.kEmprunt = kEmprunt;
	}

	public String getNomPhoto() {
		return nomPhoto;
	}


	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	
	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	@Override
	public String toString() {
		return "LivreDTO [idLivre=" + idLivre + ", titre=" + titre + ", note=" + note + ", categorie=" + categorie
				+ ", kEmprunt=" + kEmprunt + ", nomPhoto=" + nomPhoto + ", dateAjout=" + dateAjout + "]";
	}


		
	

}
