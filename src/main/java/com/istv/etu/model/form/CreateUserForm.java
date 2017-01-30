package com.istv.etu.model.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateUserForm {
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	//@Pattern(regexp="\\W*")
    private String nom;
	
    @NotEmpty(message="Le champ ne doit pas être vide.")
    //@Pattern(regexp="\\W*")
    private String prenom;
    
    @NotEmpty(message="Le champ ne doit pas être vide.")
    @Pattern(regexp="^[_A-Za-z0-9-]{6,16}$")  
    private String login;
    
    @NotEmpty(message="Le champ ne doit pas être vide.")
    @Pattern(regexp="^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")    
    private String email;
    
    @NotEmpty(message="Le champ ne doit pas être vide.")
    @Size(min=8, max=16, message="Longueur de mot de passe incorrect (entre 8 et 16 caractères)")
    @Pattern(regexp="((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16})")  
    private String password;
    
    @NotEmpty(message="Le champ ne doit pas être vide.")
    private String password2;
    
    @NotEmpty(message="Le champ ne doit pas être vide.")
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}
}
