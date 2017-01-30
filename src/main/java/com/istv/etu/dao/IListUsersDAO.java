package com.istv.etu.dao;

import java.util.List;

import com.istv.etu.model.User;
import com.istv.etu.model.form.UpdateUserForm;

public interface IListUsersDAO {
	List<User> getUsers();
	List<User> CheckAuth();
	User getOneUser(String id);
	void createUser(final User pUser);
	void deleteUser(final User pUser);
	void updateUser(final UpdateUserForm pUser, final String idUser);
	void updatePassword(final String pwd, final String idUser);
}
