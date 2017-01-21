package com.istv.etu.dao;

import java.util.List;

import com.istv.etu.model.Cours;

public interface IListCoursesDAO {
	List<Cours> getCourses();
	Cours getOneCourse(String id);
	int getOneCourseByName(String name);
	void createCourse(Cours c, final String id);
}
