package fr.bibliotheque.dto;

import java.util.Date;
import java.util.List;

import fr.bibliotheque.entities.Categorie;
import fr.bibliotheque.entities.Emprunt;
import fr.bibliotheque.entities.ProduitCulturel;

public class ProduitCulturelDTO {
	

	private Long idProduit;
	private String titre;
	private Integer note;
	private Categorie categorie;
	private boolean kEmprunt = false;
	private String nomPhoto;
	private Date dateAjout;
	
	
	 public ProduitCulturelDTO(ProduitCulturel produitCulturel){	
		this.titre=produitCulturel.getTitre();
		this.idProduit=produitCulturel.getIdProduit();
		this.categorie=produitCulturel.getCategorie();
		this.nomPhoto=produitCulturel.getNomPhoto();
		this.note=produitCulturel.getNote();
		this.dateAjout=produitCulturel.getDateAjout();
		
		List<Emprunt> emprunts = produitCulturel.getEmprunts();
		
		for(Emprunt emprunt : emprunts){		
			if(emprunt.getDateFinEmprunt()==null){
				kEmprunt=true;
				break;
			}
		}	
	 }
	


	public Long getIdProduit() {
		return idProduit;
	}



	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
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
		return "ProduitCulturelDTO [idLivre=" + idProduit + ", titre=" + titre + ", note=" + note + ", categorie="
				+ categorie + ", kEmprunt=" + kEmprunt + ", nomPhoto=" + nomPhoto + ", dateAjout=" + dateAjout + "]";
	}



		
	

}
