package com.istv.etu.dao;

import java.util.List;

import com.istv.etu.model.Cours;

public interface ICoursesDAO {
	List<Cours> getCourses();
	Cours getOneCourse(int id);
	int getOneCourseByName(String name);
	List<Cours> getCoursesOfOneUser(String id);
	void createCourse(Cours c, final String id);
	void updateCourse(final Cours cours);
	void deleteCourse(final int id);
	void validateCourse(final int id, final int idTheme);
	void deactivateCourse(final int id);
	List<Cours> getCoursesByName(String libelle);
	List<Cours> getLastCours();
	List<Cours> getCoursesByTheme(int idTheme);
	void updateNbVues(int idCours);
}
