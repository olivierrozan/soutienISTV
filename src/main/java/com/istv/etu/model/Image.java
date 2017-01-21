package com.istv.etu.model;

public class Image extends Contenu {
	
	private String location;
	private int ordre;
	private int idCours;
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
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
