package com.istv.etu.services;

import java.util.List;

import com.istv.etu.model.User;

public interface IListUsersServices {
	List<User> getUsers();
	void createUser(final String pNom, final String pPrenom);
	void deleteUser(final Integer pIdUser);
	void updateUser(final List<User> pUser);
}
