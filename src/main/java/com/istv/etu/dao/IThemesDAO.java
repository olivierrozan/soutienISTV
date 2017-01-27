package com.istv.etu.dao;

import java.util.List;

import com.istv.etu.model.Theme;;

public interface IThemesDAO {
	List<Theme> getThemes();
	void addTheme(final Theme t);
}
