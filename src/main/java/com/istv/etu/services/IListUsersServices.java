package com.istv.etu.services;

import java.util.List;

import com.istv.etu.model.User;

public interface IListUsersServices {
	Boolean checkAuth(String login, String pwd);
	List<User> getUsers();
	String getStatut(List<User> users, String id);
	int getId(List<User> users, String login);
	User getOneUser(String id);
	void createUser(final String pNom, final String pPrenom, final String pLogin, final String pPassword, final String pFormation);
	void deleteUser(final Integer pIdUser);
	void updateUser(final List<User> pUser);
}
