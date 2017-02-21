package com.istv.etu.model.form;

import java.util.ArrayList;
import java.util.List;

import com.istv.etu.model.Paragraphe;

public class UpdateCourseForm {
	
	private int idCours;
	private String libelleCours;
	private String imageTitre;	
	private List<Paragraphe> paragraphes = new ArrayList<Paragraphe>(0);
	
	public int getIdCours() {
		return idCours;
	}
	
	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}
	
	public String getLibelleCours() {
		return libelleCours;
	}
	
	public void setLibelleCours(String libelleCours) {
		this.libelleCours = libelleCours;
	}
	
	public String getImageTitre() {
		return imageTitre;
	}
	
	public void setImageTitre(String imageTitre) {
		this.imageTitre = imageTitre;
	}
	
	public List<Paragraphe> getParagraphes() {
		return paragraphes;
	}
	
	public void setParagraphes(List<Paragraphe> paragraphes) {
		this.paragraphes = paragraphes;
	}
}
