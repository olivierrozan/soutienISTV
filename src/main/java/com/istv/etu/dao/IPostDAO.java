package com.istv.etu.dao;

import java.util.List;

import com.istv.etu.model.Post;

public interface IPostDAO {
	List<Post> getPosts(int idSujet);
	void addPost(String contenu, int idSujet, String idUser);
}
