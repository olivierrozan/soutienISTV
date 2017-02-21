package com.istv.etu.services.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.istv.etu.dao.IUsersDAO;
import com.istv.etu.model.User;
import com.istv.etu.model.form.UpdateUserForm;
import com.istv.etu.services.IUsersServices;

@Service
public class UsersServices implements IUsersServices {
	
	@Autowired
    private IUsersDAO dao;
	
	@Transactional(readOnly=true)
    public Boolean checkLogin(String login, String pwd) {
        List<User> users = dao.checkLogin();
        
        //String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt(12));
		 
		// Vérification d'un mot de passe à partir du hash
		/*if (BCrypt.checkpw(pwd, hashed))
		  System.out.println("It matches : " + pwd + " -> " + hashed);*/
        
        for (User u : users) {
        	if (u.getLogin().equals(login) && BCrypt.checkpw(pwd, u.getPassword())/*u.getPassword().equals(pwd)*/) {
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
    
    public Boolean checkStatut(String id) {
    	
    	//if (id != null) {
    		String statut = dao.checkStatut(id);    		
    		return statut.equals("banni") ? false : true;
    	/*} else {
    		return false;
    	}*/
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
    
    @Transactional(readOnly=true)
    public User getOneUserWithoutCourses(String id) {
    	return dao.getOneUserWithoutCourses(id);
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
        	
        	String hashed = BCrypt.hashpw(pPassword, BCrypt.gensalt(12));
        	lUser.setPassword(hashed);
        	
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
    public void banUser(final int id, final String etat) {
        dao.banUser(id, etat);
    }
    
    @Transactional
    public void updateUser(final UpdateUserForm pUser, final String idUser) {
        dao.updateUser(pUser, idUser);        
    }
    
    @Transactional
    public String updatePassword(final String oldPwd, final String newPwd, final String newPwd2, final String idUser) {
    	
    	User u = this.getOneUserWithoutCourses(idUser);
    	String err = "";
    	//String oldPwdHashed = BCrypt.hashpw(oldPwd, BCrypt.gensalt(12));
    	
    	
    	if (BCrypt.checkpw(oldPwd, u.getPassword())/*oldPwd.equals(u.getPassword()) */&& newPwd.equals(newPwd2)) {   		
    		
    		String hashed = BCrypt.hashpw(newPwd, BCrypt.gensalt(12));
    		
    		dao.updatePassword(hashed, idUser);
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
    
    public void uploadImage(MultipartFile file, String img) {
    	if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				//String rootPath = System.getProperty("catalina.home");
				File dir = new File("C:\\eclipse\\workspace\\soutien\\soutienISTV\\src\\main\\webapp\\include\\images\\users");
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
    
    public void sendEmail(JavaMailSender mailSender, String recipientAddress, String subject, String message) {
    	         
        
        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
         
        // sends the e-mail
        mailSender.send(email);
    }
}
