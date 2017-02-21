package com.istv.etu.dao;

import java.util.List;

import com.istv.etu.model.Paragraphe;

public interface IParagraphesDAO {
	List<Paragraphe> getParagraphes(int idCours);
	void createParagraphe(final Paragraphe p);
	public void updateParagraphe(final Paragraphe par);
	int getOrderMax(final int idCours);
}
