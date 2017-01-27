package com.istv.etu.services;

import java.util.List;

import com.istv.etu.model.Theme;

public interface IThemesServices {
	List<Theme> getThemes();
	void addTheme(String libelle, int id);
}
