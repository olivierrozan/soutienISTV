package com.istv.etu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istv.etu.dao.IListThemesDAO;
import com.istv.etu.model.Theme;
import com.istv.etu.services.IListThemesServices;

@Service
public class ListThemesServices implements IListThemesServices {
	
	@Autowired
	private IListThemesDAO dao;
	
	/*@Autowired
	 private SessionFactory sessionFactory;*/
	
	@Transactional(readOnly=true)
	public List<Theme> getThemes() {
		return dao.getThemes();
		
		/*// Retrieve session from Hibernate
		  Session session = sessionFactory.getCurrentSession();
		   
		  // Create a Hibernate query (HQL)
		  Query query = session.createQuery("FROM USER");
		  
		  System.out.println(query.list().get(0));
		  
		  // Retrieve all
		  return  query.list();*/
	}
}
