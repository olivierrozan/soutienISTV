package com.istv.etu.model;

import java.util.Date;

public class Post {
		
	private int idPost;
	private Date datePost;
	
	private String contenu;
	
	private Sujet sujet = new Sujet();
	private User user = new User();
	
	public int getIdPost() {
		return idPost;
	}
	
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	
	public Date getDatePost() {
		return datePost;
	}
	
	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}
	
	public String getContenu() {
		return contenu;
	}
	
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public Sujet getSujet() {
		return sujet;
	}
	
	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
