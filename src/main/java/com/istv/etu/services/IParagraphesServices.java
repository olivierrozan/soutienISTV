package com.istv.etu.services;

import java.util.List;

import com.istv.etu.model.Paragraphe;
import com.istv.etu.model.form.UpdateParagrapheForm;

public interface IParagraphesServices {
	public List<Paragraphe> getParagraphes(int idCours);
	public void createParagraphe(final String texte, final String img, final int ordre, final int idCours);
	public void updateParagraphe(UpdateParagrapheForm par);
	int getOrderMax(final int idCours);
}
