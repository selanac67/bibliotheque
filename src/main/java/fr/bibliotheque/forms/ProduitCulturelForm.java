package fr.bibliotheque.forms;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class ProduitCulturelForm {
	
	private Long idProduit;	
	
	private String produitType;
	
	private Integer note;
	
	private String nomPhoto;

	public String getNomPhoto() {
		return nomPhoto;
	}

	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}

	@NotEmpty
	private String titre;
	
	@NotEmpty
	private MultipartFile inputImage;
	
	@NotEmpty
	private Long idCategorie;
	
	public Long getIdProduitForm() {
		return idProduit;
	}

	public void setIdProduitForm(Long idLivreForm) {
		this.idProduit = idLivreForm;
	}
	

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public MultipartFile getInputImage() {
		return inputImage;
	}

	public void setInputImage(MultipartFile inputImage) {
		this.inputImage = inputImage;
	}

	public Long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public String getProduitType() {
		return produitType;
	}

	public void setProduitType(String produitType) {
		this.produitType = produitType;
	}




}
