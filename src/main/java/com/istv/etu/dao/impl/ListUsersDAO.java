package com.istv.etu.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.istv.etu.dao.IListUsersDAO;
import com.istv.etu.model.User;

@Repository
public class ListUsersDAO implements IListUsersDAO {
	
	@PersistenceContext
    private EntityManager entityManager;

    public List<User> getUsers() {
        final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();

        final CriteriaQuery<User> lCriteriaQuery = lCriteriaBuilder.createQuery(User.class);
        final Root<User> lRoot = lCriteriaQuery.from(User.class);
        lCriteriaQuery.select(lRoot);
        final TypedQuery<User> lTypedQuery = entityManager.createQuery(lCriteriaQuery);

        return lTypedQuery.getResultList();
    }
    
    public void createUser(final User pUser) {
        entityManager.persist(pUser);
    }
    
    public void deleteUser(final User pUser) {
    	final User lUser = entityManager.getReference(User.class, pUser.getId());
        entityManager.remove(lUser);
    }
    
    public void updateUser(final User pUser) {
    	final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();

        final CriteriaUpdate<User> lCriteriaUpdate = lCriteriaBuilder.createCriteriaUpdate(User.class);
        final Root<User> lRoot = lCriteriaUpdate.from(User.class);
        final Path<User> lPath = lRoot.get("id");
        final Expression<Boolean> lExpression = lCriteriaBuilder.equal(lPath, pUser.getId());
        lCriteriaUpdate.where(lExpression);
        lCriteriaUpdate.set("nom", pUser.getNom());
        final Query lQuery = entityManager.createQuery(lCriteriaUpdate);
        final int lRowCount = lQuery.executeUpdate();

        if (lRowCount != 1) {
            final org.hibernate.Query lHQuery = lQuery.unwrap(org.hibernate.Query.class);
            final String lSql = lHQuery.getQueryString();
            throw new RuntimeException("Nombre d'occurences (" + lRowCount + 
                    ") modifiés différent de 1 pour " + lSql);
        }
    }
}
