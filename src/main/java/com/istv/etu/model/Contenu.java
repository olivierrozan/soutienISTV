package com.istv.etu.model;

public class Contenu {
	
	protected int idContenu;
	protected int ordre;
	protected int fk_idCours;
	
	public int getIdContenu() {
		return idContenu;
	}
	
	public void setIdContenu(int idContenu) {
		this.idContenu = idContenu;
	}
	
	public int getOrdre() {
		return ordre;
	}
	
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	
	public int getFk_idCours() {
		return fk_idCours;
	}
	
	public void setFk_idCours(int fk_idCours) {
		this.fk_idCours = fk_idCours;
	}
	
	
}
