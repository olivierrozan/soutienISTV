package com.istv.etu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istv.etu.dao.IParagraphesDAO;
import com.istv.etu.model.Paragraphe;
import com.istv.etu.model.form.UpdateParagrapheForm;
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
	public void createParagraphe(final String texte, final String img, final int ordre, final int idCours) {
		Paragraphe p = new Paragraphe();
		
		p.setTexte(texte);
		p.setImageLocation(img);
		p.setOrdre(ordre);
		p.setFk_idCours(idCours);
				
		dao.createParagraphe(p);
	}
	
	@Transactional
	public void updateParagraphe(UpdateParagrapheForm par) {
		Paragraphe p = new Paragraphe();
		
		p.setTexte(par.getTexte());
		p.setImageLocation(par.getImageLocation());
		p.setIdParagraphe(par.getIdParagraphe());
		
		dao.updateParagraphe(p);
	}
	
	@Transactional(readOnly=true)
	public int getOrderMax(final int idCours){
		return dao.getOrderMax(idCours);
	}
}
