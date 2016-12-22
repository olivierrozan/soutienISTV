package com.istv.etu.model.form;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class CreateUserForm {
	
	@NotEmpty
	//@Pattern(regexp="\\W*")
    private String nom;
    @NotEmpty
    //@Pattern(regexp="\\W*")
    private String prenom;

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
}
