package com.istv.etu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istv.etu.dao.IImagesDAO;
import com.istv.etu.model.Image;
import com.istv.etu.services.IImagesServices;

@Service
public class ImagesServices implements IImagesServices {
	
	@Autowired
	private IImagesDAO dao;
	
	@Transactional(readOnly=true)
	public List<Image> getImages(int idCours) {
		return dao.getImages(idCours);
	}
	
	@Transactional
	public void createImage(final String location, final int ordre, final int idCours) {
		Image i = new Image();
		i.setLocation(location);
		i.setOrdre(ordre);
		i.setIdCours(idCours);
		
		dao.createImage(i);
	}
}
