package com.istv.etu.services;

import java.util.List;

import com.istv.etu.model.Cours;

public interface ICoursesServices {
	List<Cours> getCourses();
	Cours getOneCourse(String id);
	int getOneCourseByName(String name);
	void createCourse(final String libelle, final String img, final String id);
	void deleteCourse(final String id);
	void validateCourse(final String id);
}
