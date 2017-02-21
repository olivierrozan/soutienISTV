package com.istv.etu.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.istv.etu.model.Cours;
import com.istv.etu.model.form.CreateCourseForm;
import com.istv.etu.model.form.UpdateCourseForm;

public interface ICoursesServices {
	List<Cours> getCourses();
	Cours getOneCourse(int id);
	int getOneCourseByName(String name);
	List<Cours> getCoursesOfOneUser(String id);
	void createCourse(final String libelle, final String img, final String id);
	void updateCourse(final UpdateCourseForm cours);
	void deleteCourse(final int id);
	void validateCourse(final int id, final int idTheme);
	void deactivateCourse(final int id);
	List<Cours> getCoursesByName(String libelle);
	List<Cours> getLastCours();
	List<Cours> getCoursesByTheme(int idTheme);
	void uploadImage(MultipartFile file, String img);
	void uploadImages(MultipartFile[] files);
	void updateNbVues(int idCours);
}
