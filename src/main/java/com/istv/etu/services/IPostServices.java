package com.istv.etu.services;

import java.util.List;

import com.istv.etu.model.Post;

public interface IPostServices {
	List<Post> getPosts(int idSujet);
}