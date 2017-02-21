package com.istv.etu.model.form;

public class UpdateParagrapheForm {
	
	private int idParagraphe;
	private String texte;
	private String imageLocation;
	
	public int getIdParagraphe() {
		return idParagraphe;
	}
	
	public void setIdParagraphe(int idParagraphe) {
		this.idParagraphe = idParagraphe;
	}
	
	public String getTexte() {
		return texte;
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public String getImageLocation() {
		return imageLocation;
	}
	
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}	
}
