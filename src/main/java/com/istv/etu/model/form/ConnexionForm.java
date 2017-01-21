package com.istv.etu.model.form;

import org.hibernate.validator.constraints.NotEmpty;

public class ConnexionForm {
	@NotEmpty
    private String login;
    @NotEmpty
    private String password;
    
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
