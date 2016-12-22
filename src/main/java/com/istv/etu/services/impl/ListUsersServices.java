package com.istv.etu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istv.etu.dao.IListUsersDAO;
import com.istv.etu.model.User;
import com.istv.etu.services.IListUsersServices;

@Service
public class ListUsersServices implements IListUsersServices {
	
	@Autowired
    private IListUsersDAO dao;

    @Transactional(readOnly=true)
    public List<User> getUsers() {
        return dao.getUsers();
    }
    
    @Transactional
    public void createUser(final String pNom, final String pPrenom) {
    	final User lUser = new User();
    	lUser.setNom(pNom);
    	lUser.setPrenom(pPrenom);

        dao.createUser(lUser);
    }
    
    @Transactional
    public void deleteUser(final Integer pIdUser) {
        final User lCourse = new User();
        lCourse.setId(pIdUser);

        dao.deleteUser(lCourse);
    }
    
    @Transactional
    public void updateUser(final List<User> pUser) {
    	for (final User lUser : pUser) {
            dao.updateUser(lUser);
        }
    }
}
