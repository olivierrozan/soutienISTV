package com.istv.etu.dao;

import java.util.List;

import com.istv.etu.model.User;

public interface IListUsersDAO {
	List<User> getUsers();
	List<User> CheckAuth();
	User getOneUser(String id);
	void createUser(final User pUser);
	void deleteUser(final User pUser);
	void updateUser(final User pUser);
}
