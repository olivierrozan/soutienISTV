package com.istv.etu.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.istv.etu.dao.IListUsersDAO;
import com.istv.etu.model.User;
import com.istv.etu.model.form.UpdateUserForm;
import com.istv.etu.services.IListUsersServices;

@Service
public class ListUsersServices implements IListUsersServices {
	
	@Autowired
    private IListUsersDAO dao;
	
	@Transactional(readOnly=true)
    public Boolean checkAuth(String login, String pwd) {
        List<User> users = dao.CheckAuth();
        
        for (User u : users) {
        	if (u.getLogin().equals(login) && u.getPassword().equals(pwd)) {
        		System.out.println("Auth OK");
        		return true;
        	}
        }
        
        System.out.println("Auth Failed");
	 	return false;
    }

    @Transactional(readOnly=true)
    public List<User> getUsers() {
        return dao.getUsers();
    }
    
    @Transactional(readOnly=true)
    public String getStatut(List<User> users, String login) {
    	
    	String statut = "";
    	
    	for (User u: users) {
    		if (u.getLogin().equals(login)) {
    			statut = u.getStatut();
    		}
    	}
    	
    	return statut;
    }
    
    @Transactional(readOnly=true)
    public int getId(List<User> users, String login) {
    	
    	int id = 0;
    	
    	for (User u: users) {
    		if (u.getLogin().equals(login)) {
    			id = u.getId();
    		}
    	}
    	
    	return id;
    }
    
    @Transactional(readOnly=true)
    public User getOneUser(String id) {
    	return dao.getOneUser(id);
    }
    
    @Transactional
    public Boolean createUser(final String pNom, final String pPrenom, final String pLogin, final String pEmail, final String pPassword, final String pPassword2, final String pFormation) {
    	
    	if (pPassword.equals(pPassword2)) {
    		final User lUser = new User();
        	
        	lUser.setNom(pNom);
        	lUser.setPrenom(pPrenom);
        	lUser.setDateDerniereModif(new Date());
        	lUser.setLogin(pLogin);
        	lUser.setEmail(pEmail);
        	lUser.setPassword(pPassword);
        	lUser.setStatut("user");
        	lUser.setAvatar("avatar.png");
        	lUser.setFormation(pFormation);
        	
            dao.createUser(lUser);
            
            return true;
    	} else {
    		return false;
    	}
    	
    }
    
    @Transactional
    public void deleteUser(final Integer pIdUser) {
        final User lCourse = new User();
        lCourse.setId(pIdUser);

        dao.deleteUser(lCourse);
    }
    
    @Transactional
    public void updateUser(final UpdateUserForm pUser, final String idUser) {
        dao.updateUser(pUser, idUser);        
    }
    
    @Transactional
    public String updatePassword(final String oldPwd, final String newPwd, final String newPwd2, final String idUser) {
    	
    	User u = this.getOneUser(idUser);
    	String err = "";
    	
    	if (oldPwd.equals(u.getPassword()) && newPwd.equals(newPwd2)) {
    		dao.updatePassword(newPwd, idUser);
    		err = "ok";
    	} else {
    		if (!oldPwd.equals(u.getPassword())) {
    			
    			err = "Mauvais mot de passe";
    		}
    		
    		if (!newPwd.equals(newPwd2)) {
    			
    			err = "Mots de passe différents";
    		}
    	}
		
    	return err;
    }
}
