package com.istv.etu.dao;

import java.util.List;

import com.istv.etu.model.Image;

public interface IImagesDAO {
	List<Image> getImages(int idCours);
	void createImage(final Image i);
}
