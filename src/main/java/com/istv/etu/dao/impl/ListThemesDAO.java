package com.istv.etu.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.istv.etu.dao.IListThemesDAO;
import com.istv.etu.model.Theme;

@Repository
public class ListThemesDAO implements IListThemesDAO {
	
	@PersistenceContext
    private EntityManager entityManager;
	
    public List<Theme> getThemes() {
        
    	final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();

        final CriteriaQuery<Theme> lCriteriaQuery = lCriteriaBuilder.createQuery(Theme.class);
        final Root<Theme> lRoot = lCriteriaQuery.from(Theme.class);
        lCriteriaQuery.select(lRoot);
        final TypedQuery<Theme> lTypedQuery = entityManager.createQuery(lCriteriaQuery);

        return lTypedQuery.getResultList();
    }
}
