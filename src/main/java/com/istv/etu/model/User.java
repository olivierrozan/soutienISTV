package com.istv.etu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDUSER")
    private Integer id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private Date dateDerniereModif;
    private String statut;
    private String avatar;
    private String formation;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
    private List<Cours> cours = new ArrayList<Cours>(0);
    
    public Integer getId() {
        return id;
    }

    public void setId(final Integer pId) {
        id = pId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String pNom) {
        nom = pNom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(final String pPrenom) {
        prenom = pPrenom;
    }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getDateDerniereModif() {
		return dateDerniereModif;
	}

	public void setDateDerniereModif(Date dateDerniereModif) {
		this.dateDerniereModif = dateDerniereModif;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}
}
