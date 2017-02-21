package com.istv.etu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istv.etu.dao.ISujetsDAO;
import com.istv.etu.model.Sujet;
import com.istv.etu.services.ISujetsServices;

@Service
public class SujetsServices implements ISujetsServices {
	
	@Autowired
	private ISujetsDAO dao;
	
	@Transactional(readOnly=true)
	public List<Sujet> getSujets(int id) {
		return dao.getSujets(id);
	}
	
	@Transactional(readOnly=true)
	public List<Sujet> getSujetsByName(String libelle) {
		return dao.getSujetsByName(libelle);
	}
	
	@Transactional
	public void createSujet(Sujet s, String id) {
		dao.createSujet(s, id);
	}
	
	public int getSujetId(String libelle) {
		return dao.getSujetId(libelle);
	}
	
	@Transactional
	public List<Sujet> getLastSujets() {
		return dao.getLastSujets();
	}
}
