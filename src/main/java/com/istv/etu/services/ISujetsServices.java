package com.istv.etu.services;

import java.util.List;

import com.istv.etu.model.Sujet;

public interface ISujetsServices {
	List<Sujet> getSujets(int id);
	List<Sujet> getSujetsByName(String libelle);
	void createSujet(Sujet s, String id);
	int getSujetId(String libelle);
	List<Sujet> getLastSujets();
}
