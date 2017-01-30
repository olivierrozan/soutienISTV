package com.istv.etu.model.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class UpdateUserForm {
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
    private String nom;
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
    private String prenom;
    
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Pattern(regexp="^[_A-Za-z0-9-]{6,16}$")  
    private String login;
    
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Pattern(regexp="^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")    
    private String email;
    
    private String avatar;
    
	@NotEmpty(message="Le champ ne doit pas être vide.")
    private String formation;
    
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getFormation() {
		return formation;
	}
	
	public void setFormation(String formation) {
		this.formation = formation;
	}
}
