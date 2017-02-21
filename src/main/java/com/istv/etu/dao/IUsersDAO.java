package com.istv.etu.dao;

import java.util.List;

import com.istv.etu.model.User;
import com.istv.etu.model.form.UpdateUserForm;

public interface IUsersDAO {
	List<User> getUsers();
	List<User> checkLogin();
	String checkStatut(String id);
	User getOneUser(String id);
	User getOneUserWithoutCourses(String id);
	void createUser(final User pUser);
	void banUser(final int id, final String etat);
	void updateUser(final UpdateUserForm pUser, final String idUser);
	void updatePassword(final String pwd, final String idUser);
}
