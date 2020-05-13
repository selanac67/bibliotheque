package fr.bibliotheque.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "PRODUIT_CULTUREL")
@DiscriminatorColumn(name = "PRODUIT_TYPE")
public  class ProduitCulturel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2221478747089655477L;

	@Id
	@GeneratedValue
	private Long idProduit;

	@Column(name = "produit_type", insertable = false, updatable = false)
	protected String produitType;

	private String titre;

	private String nomPhoto;

	private Integer note;

	private Date dateAjout;

	@OneToMany(mappedBy = "produitCulturel")
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

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
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
		emprunt.setProduitCulturel(this);
	}

	public void removeEmprunt(Emprunt emprunt) {
		emprunts.remove(emprunt);
		emprunt.setProduitCulturel(null);
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

	public String getProduitType() {
		return produitType;
	}

}
