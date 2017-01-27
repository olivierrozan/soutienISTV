package com.istv.etu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istv.etu.dao.IPostDAO;
import com.istv.etu.model.Post;
import com.istv.etu.services.IPostServices;

@Service
public class PostServices implements IPostServices {
	
	@Autowired
	private IPostDAO dao;
	
	public List<Post> getPosts(int idSujet) {
		return dao.getPosts(idSujet);
	}
	
	public void addPost(String contenu, int idSujet, String idUser) {
		dao.addPost(contenu, idSujet, idUser);
	}
}
