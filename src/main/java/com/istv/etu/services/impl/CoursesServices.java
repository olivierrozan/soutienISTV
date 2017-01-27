package com.istv.etu.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istv.etu.dao.ICoursesDAO;
import com.istv.etu.model.Cours;
import com.istv.etu.services.ICoursesServices;

@Service
public class CoursesServices implements ICoursesServices {
	
	@Autowired
	private ICoursesDAO dao;
	
	@Transactional(readOnly=true)
	public List<Cours> getCourses() {
		return dao.getCourses();
	}
	
	@Transactional(readOnly=true)
	public Cours getOneCourse(String id) {
		return dao.getOneCourse(id);
	}
	
	@Transactional(readOnly=true)
	public int getOneCourseByName(String name) {
		return dao.getOneCourseByName(name);
	}
	
	@Transactional
	public void createCourse(final String libelle, final String img, final String id) {
		final Cours c = new Cours();
		
		c.setLibelleCours(libelle);
		c.setDateDerniereModif(new Date());
		c.setImageTitre(img);
		c.setNbVues(0);
		
		dao.createCourse(c, id);
	}
	
	@Transactional
	public void deleteCourse(final String id) {
		dao.deleteCourse(id);
	}
	
	@Transactional
	public void validateCourse(final String id) {
		dao.validateCourse(id);
	}
}
