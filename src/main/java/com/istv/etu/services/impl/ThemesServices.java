package com.istv.etu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istv.etu.dao.IThemesDAO;
import com.istv.etu.model.Theme;
import com.istv.etu.services.IThemesServices;

@Service
public class ThemesServices implements IThemesServices {
	
	@Autowired
	private IThemesDAO dao;
	
	@Transactional(readOnly=true)
	public List<Theme> getThemes() {
		return dao.getThemes();
	}
	
	@Transactional
	public void addTheme(String libelle, int id) {
		Theme t = new Theme();
		
		t.setLibelleTheme(libelle);
		t.setFk_idUser(id);
		
		dao.addTheme(t);
	}
}
