package com.istv.etu.model.form;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateUserForm {
	
	@NotEmpty
	//@Pattern(regexp="\\W*")
    private String nom;
    @NotEmpty
    //@Pattern(regexp="\\W*")
    private String prenom;
    
    @NotEmpty
	//@Pattern(regexp="\\W*")
    private String login;
    
    @NotEmpty
    
    private String password;
    
    @NotEmpty
    //@Pattern(regexp="\\W*")
    private String formation;

    public String getNom() {
        return nom;
    }

    public void setNom(final String pNom) {
        nom = pNom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(final String pPrenom) {
        prenom = pPrenom;
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

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}
}
