package com.istv.etu.model.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UpdatePasswordForm {
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Size(min=8, max=16, message="Mot de passe trop court")
	private String oldPassword;
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Size(min=8, max=16, message="Mot de passe trop court")
	private String newPassword;
	
	@NotEmpty(message="Le champ ne doit pas être vide.")
	@Size(min=8, max=16, message="Mot de passe trop court")	
	private String newPassword2;
	
	public String getOldPassword() {
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getNewPassword2() {
		return newPassword2;
	}
	
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
}
