package com.istv.etu.model;

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
@Table(name="PARAGRAPHE")
public class Paragraphe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDPARAGRAPHE")
	private int idParagraphe;
	private String texte;
	private String imageLocation;
	private int ordre;
	private int fk_idCours;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="FK_IDCOURS")
	private Cours cours;
	
	public int getIdParagraphe() {
		return idParagraphe;
	}

	public void setIdParagraphe(int idParagraphe) {
		this.idParagraphe = idParagraphe;
	}

	public String getTexte() {
		return texte;
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public int getOrdre() {
		return ordre;
	}
	
	public int getFk_idCours() {
		return fk_idCours;
	}

	public void setFk_idCours(int fk_idCours) {
		this.fk_idCours = fk_idCours;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}
}
