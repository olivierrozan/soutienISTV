package com.istv.etu.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Sujet {
	
	private int id;
	
	@NotEmpty
	@Size(min=8, max=64)
	private String titre;
	private String etat;
	private Date dateCreation;
	private int fk_idTheme;
	private int fk_idUser;
	
	private User user = new User();
	private Theme theme = new Theme();
	
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
	
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
}
