package com.istv.etu.services.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.istv.etu.dao.ICoursesDAO;
import com.istv.etu.model.Cours;
import com.istv.etu.model.Paragraphe;
import com.istv.etu.model.form.CreateCourseForm;
import com.istv.etu.model.form.UpdateCourseForm;
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
	public Cours getOneCourse(int id) {
		return dao.getOneCourse(id);
	}
	
	@Transactional(readOnly=true)
	public List<Cours> getCoursesOfOneUser(String id) {
		return dao.getCoursesOfOneUser(id);
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
	public void updateCourse(final UpdateCourseForm cours) {
		Cours c = new Cours();
		c.setIdCours(cours.getIdCours());
		c.setLibelleCours(cours.getLibelleCours());
		c.setImageTitre(cours.getImageTitre());
		
		List<Paragraphe> p = new ArrayList<Paragraphe>(0);
		c.setParagraphes(p);
		
		dao.updateCourse(c);
	}
	
	@Transactional
	public void deleteCourse(final int id) {
		dao.deleteCourse(id);
	}
	
	@Transactional
	public void validateCourse(final int id, final int idTheme) {
		dao.validateCourse(id, idTheme);
	}
	
	@Transactional
	public void deactivateCourse(final int id) {
		dao.deactivateCourse(id);
	}
	
	@Transactional(readOnly=true)
	public List<Cours> getCoursesByName(String libelle) {
		return dao.getCoursesByName(libelle);
	}
	
	@Transactional(readOnly=true)
	public List<Cours> getLastCours() {
		return dao.getLastCours();
	}
	
	@Transactional(readOnly=true)
	public List<Cours> getCoursesByTheme(int idTheme) {
		return dao.getCoursesByTheme(idTheme);
	}
	
	@Transactional
	public void uploadImage(MultipartFile file, String img) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				//String rootPath = System.getProperty("catalina.home");
				//File dir = new File(rootPath + File.separator + "wtpwebapps\\soutienISTV\\include\\images\\cours");
				File dir = new File("C:\\eclipse\\workspace\\soutien\\soutienISTV\\src\\main\\webapp\\include\\images\\cours");
				//System.out.println(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println( "You successfully uploaded file=" + img);
			} catch (Exception e) {
				System.out.println("You failed to upload " + img + " => " + e.getMessage());
			}
		} else {
			System.out.println("You failed to upload " + img + " because the file was empty.");
		}
	}
	
	@Transactional
	public void uploadImages(MultipartFile[] files) {
				
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = files[i].getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println("You successfully uploaded file=" + name);
			} catch (Exception e) {
				System.out.println( "You failed to upload " + name + " => " + e.getMessage());
			}
		}
	}
	
	public void updateNbVues(int idCours) {
		dao.updateNbVues(idCours);
	}
}
