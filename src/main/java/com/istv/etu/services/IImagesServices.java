package com.istv.etu.services;

import java.util.List;

import com.istv.etu.model.Image;

public interface IImagesServices {
	List<Image> getImages(int idCours);
	void createImage(final String location, final int ordre, final int idCours);
}
