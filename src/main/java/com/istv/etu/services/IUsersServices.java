package com.istv.etu.services;

import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.MultipartFile;

import com.istv.etu.model.User;
import com.istv.etu.model.form.CreateCourseForm;
import com.istv.etu.model.form.UpdateUserForm;

public interface IUsersServices {
	Boolean checkLogin(String login, String pwd);
	List<User> getUsers();
	String getStatut(List<User> users, String id);
	Boolean checkStatut(String id);
	int getId(List<User> users, String login);
	User getOneUser(String id);
	User getOneUserWithoutCourses(String id);
	Boolean createUser(final String pNom, final String pPrenom, final String pLogin, final String pEmail, final String pPassword, final String pPassword2, final String pFormation);
	void banUser(final int id, final String etat);
	void updateUser(final UpdateUserForm pUser, final String idUser);
	String updatePassword(final String oldPwd, final String newPwd, final String newPwd2, final String idUser);
	void uploadImage(MultipartFile file, String img);
	void sendEmail(JavaMailSender mailSender, String recipientAddress, String subject, String message);
}
