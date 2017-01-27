package com.istv.etu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COURS")
public class Cours {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDCOURS")
	private int idCours;
	private String imageTitre;
	private String libelleCours;
	private Date dateDerniereModif;
	private String etat;
	private int nbVues;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="FK_IDUSER")
	private User user;
	
	public int getIdCours() {
		return idCours;
	}
	
	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}
	
	public String getImageTitre() {
		return imageTitre;
	}
	
	public void setImageTitre(String imageTitre) {
		this.imageTitre = imageTitre;
	}
	
	public String getLibelleCours() {
		return libelleCours;
	}
	
	public void setLibelleCours(String plibelleCours) {
		this.libelleCours = plibelleCours;
	}
	
	public Date getDateDerniereModif() {
		return dateDerniereModif;
	}
	
	public void setDateDerniereModif(Date pdateDerniereModif) {
		this.dateDerniereModif = pdateDerniereModif;
	}
	
	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getNbVues() {
		return nbVues;
	}
	
	public void setNbVues(int nbVues) {
		this.nbVues = nbVues;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
