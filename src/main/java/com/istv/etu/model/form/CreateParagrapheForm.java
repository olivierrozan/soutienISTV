package com.istv.etu.model.form;

import java.util.ArrayList;
import java.util.List;

import com.istv.etu.model.Paragraphe;

public class CreateParagrapheForm {
	
	private List<Paragraphe> paragraphes;
	private String[] images;
	private List<String> textes;
	private Integer fk_idCours;
	
	public CreateParagrapheForm() {
		this.paragraphes = new ArrayList<Paragraphe>();
	}

	public List<Paragraphe> getParagraphes() {
		return paragraphes;
	}

	public void setParagraphes(List<Paragraphe> paragraphes) {
		this.paragraphes = paragraphes;
	}
	
	public void add(Paragraphe p) {
		this.paragraphes.add(p);
	}
	
	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public List<String> getTextes() {
		return textes;
	}

	public void setTextes(List<String> textes) {
		this.textes = textes;
	}

	public Integer getFk_idCours() {
		return fk_idCours;
	}

	public void setFk_idCours(Integer fk_idCours) {
		this.fk_idCours = fk_idCours;
	}
	
}
