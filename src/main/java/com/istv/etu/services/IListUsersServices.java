package com.istv.etu.services;

import java.util.List;

import com.istv.etu.model.User;
import com.istv.etu.model.form.UpdateUserForm;

public interface IListUsersServices {
	Boolean checkAuth(String login, String pwd);
	List<User> getUsers();
	String getStatut(List<User> users, String id);
	int getId(List<User> users, String login);
	User getOneUser(String id);
	Boolean createUser(final String pNom, final String pPrenom, final String pLogin, final String pEmail, final String pPassword, final String pPassword2, final String pFormation);
	void deleteUser(final Integer pIdUser);
	void updateUser(final UpdateUserForm pUser, final String idUser);
	String updatePassword(final String oldPwd, final String newPwd, final String newPwd2, final String idUser);
}
