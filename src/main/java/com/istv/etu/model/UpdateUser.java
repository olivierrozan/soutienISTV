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
}
