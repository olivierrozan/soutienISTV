package com.istv.etu.services;

import java.util.List;

import com.istv.etu.model.Paragraphe;

public interface IParagraphesServices {
	public List<Paragraphe> getParagraphes(int idCours);
	public void createParagraphe(final String texte, final int ordre, final int idCours);
}
