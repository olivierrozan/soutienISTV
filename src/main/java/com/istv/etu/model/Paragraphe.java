package com.istv.etu.model;

public class Paragraphe extends Contenu {
	
	private String texte;
	private int ordre;
	private int idCours;
	
	public String getTexte() {
		return texte;
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public int getOrdre() {
		return ordre;
	}
	
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public int getIdCours() {
		return idCours;
	}

	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}
}