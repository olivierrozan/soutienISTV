package com.istv.etu.model;

public class Sujet {
	
	private int id;
	private String titre;
	private String etat;
	private int fk_idTheme;
	private int fk_idUser;
	
	private User user = new User();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getEtat() {
		return etat;
	}
	
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	public int getFk_idTheme() {
		return fk_idTheme;
	}
	
	public void setFk_idTheme(int fk_idTheme) {
		this.fk_idTheme = fk_idTheme;
	}
	
	public int getFk_idUser() {
		return fk_idUser;
	}
	
	public void setFk_idUser(int fk_idUser) {
		this.fk_idUser = fk_idUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
