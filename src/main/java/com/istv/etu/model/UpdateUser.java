package com.istv.etu.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class UpdateUser {
	
	private Integer id;
	@NotEmpty(message="{modification.user.nom.notempty}")
    @Pattern(regexp="\\W*", message="{modification.user.nom.numerique}")
    private String nom;
    @NotEmpty(message="{modification.user.prenom.notempty}")
    @Pattern(regexp="\\W*", message="{modification.user.prenom.numerique}")
    private String prenom;
    private String login;
    private String password;
    private String email;
    private String avatar;
    private String formation;
    
	public Integer getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
