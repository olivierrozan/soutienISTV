package com.istv.etu.dao;

import java.util.List;

import com.istv.etu.model.Sujet;

public interface ISujetsDAO {
	List<Sujet> getSujets(int id);
	List<Sujet> getSujetsByName(String libelle);
	void createSujet(Sujet s, String id);
	int getSujetId(String libelle);
	List<Sujet> getLastSujets();
}
