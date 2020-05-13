package fr.bibliotheque.entities;

public enum EProduitType { 
	
	L("L","livre","livres"), J("J","jeu","jeux");

	private String discriminatorValue;
	private String nomCourtSingulier;
	private String nomCourtPluriel;

	private EProduitType(String discriminatorValue,String nomCourtSingulier,String nomCourtPluriel) {
		this.discriminatorValue = discriminatorValue;
		this.nomCourtSingulier=nomCourtSingulier;
		this.nomCourtPluriel=nomCourtPluriel;
	}

	public String getDiscriminatorValue() {
		return this.discriminatorValue;
	}

	public String getNomCourtSingulier() {
		return nomCourtSingulier;
	}

	public String getNomCourtPluriel() {
		return nomCourtPluriel;
	}
	
	
	
	
}
