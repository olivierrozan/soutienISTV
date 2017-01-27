package com.istv.etu.model.form;

import java.util.ArrayList;
import java.util.List;

import com.istv.etu.model.Image;
import com.istv.etu.model.Paragraphe;

public class CreateParagrapheForm {
	
	private List<Paragraphe> paragraphes;
	private List<Image> images;
	private Integer fk_idCours;
	
	public CreateParagrapheForm() {
		this.paragraphes = new ArrayList<Paragraphe>();
		this.images = new ArrayList<Image>();
	}

	public List<Paragraphe> getParagraphes() {
		return paragraphes;
	}

	public void setParagraphes(List<Paragraphe> paragraphes) {
		this.paragraphes = paragraphes;
	}
	
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void add(Paragraphe p) {
		this.paragraphes.add(p);
	}
	
	public void add(Image i) {
		this.images.add(i);
	}

	public Integer getFk_idCours() {
		return fk_idCours;
	}

	public void setFk_idCours(Integer fk_idCours) {
		this.fk_idCours = fk_idCours;
	}
}
