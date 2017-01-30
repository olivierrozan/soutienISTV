package com.istv.etu.model.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateCourseForm {
	
	private String imageTitre;	
	
	@NotEmpty
	@Size(min=8, max=64)
	private String libelleCours;
	
	private String dateDerniereModif;
	
	@NotNull
	private int nbVues;
	
	//List<CreateParagrapheForm> paragraphes = new ArrayList<CreateParagrapheForm>(0);
	
	public String getImageTitre() {
		return imageTitre;
	}
	
	public void setImageTitre(String imageTitre) {
		this.imageTitre = imageTitre;
	}
	
	public String getLibelleCours() {
		return libelleCours;
	}
	
	public void setLibelleCours(String libelleCours) {
		this.libelleCours = libelleCours;
	}
	
	public String getDateDerniereModif() {
		return dateDerniereModif;
	}
	
	public void setDateDerniereModif(String dateDerniereModif) {
		this.dateDerniereModif = dateDerniereModif;
	}
	
	public int getNbVues() {
		return nbVues;
	}
	
	public void setNbVues(int nbVues) {
		this.nbVues = nbVues;
	}

	/*public List<CreateParagrapheForm> getParagraphes() {
		return paragraphes;
	}

	public void setParagraphes(List<CreateParagrapheForm> paragraphes) {
		this.paragraphes = paragraphes;
	}*/
	
	
}
