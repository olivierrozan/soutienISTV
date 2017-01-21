package com.istv.etu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istv.etu.dao.IParagraphesDAO;
import com.istv.etu.model.Paragraphe;
import com.istv.etu.services.IParagraphesServices;

@Service
public class ParagraphesServices implements IParagraphesServices {
	
	@Autowired
	private IParagraphesDAO dao;
	
	@Transactional(readOnly=true)
	public List<Paragraphe> getParagraphes(int idCours) {
		return dao.getParagraphes(idCours);
	}
	
	@Transactional
	public void createParagraphe(final String texte, final int ordre, final int idCours) {
		Paragraphe p = new Paragraphe();
		
		p.setTexte(texte);
		p.setOrdre(ordre);
		p.setIdCours(idCours);
		
		dao.createParagraphe(p);
	}
}
