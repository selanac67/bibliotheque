package fr.bibliotheque.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Livre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6005777793849549630L;

	@Id
	@GeneratedValue
	private Long idLivre;

	private String titre;

	private String nomPhoto;

	private Integer note;

	private Date dateAjout;

	@OneToMany(mappedBy = "livre")
	private List<Emprunt> emprunts = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Categorie categorie;

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getNomPhoto() {
		return nomPhoto;
	}

	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}

	public Long getIdLivre() {
		return idLivre;
	}

	public void setIdLivre(Long idLivre) {
		this.idLivre = idLivre;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(List<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

	public void addEmprunt(Emprunt emprunt) {
		emprunts.add(emprunt);
		emprunt.setLivre(this);
	}

	public void removeEmprunt(Emprunt emprunt) {
		emprunts.remove(emprunt);
		emprunt.setLivre(null);
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

}
