package com.istv.etu.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.istv.etu.model.User;

@Controller
public class FileController implements ServletContextAware {
	
	private ServletContext servletContext;
	
	@RequestMapping(value = "/person/add", method = RequestMethod.POST)
	public String addPersonFromForm(@Valid User user,
	BindingResult bindingResult,
	@RequestParam(value = "avatar", required = false) MultipartFile image) {
	 
	if (!image.isEmpty()) {
	try {
	validateImage(image);
	 
	} catch (RuntimeException re) {
	bindingResult.reject(re.getMessage());
	System.out.println("no validate : " + re.getMessage());
	return "redirect:/error403";
	}
	 
	try {
	saveImage(user.getAvatar() + ".png", image);
	} catch (IOException e) {
	bindingResult.reject(e.getMessage());
	System.out.println("no save : " + e.getMessage());
	return "redirect:/error403";
	}
	}
	 
	
	return "redirect:/";
	}
	 
	private void validateImage(MultipartFile image) {
	if (!image.getContentType().equals("image/png")) {
	throw new RuntimeException("Only png images are accepted");
	}
	}
	 
	public void setServletContext(ServletContext servletContext) {
	this.servletContext = servletContext;
	 
	}
	 
	private void saveImage(String filename, MultipartFile image)
	throws RuntimeException, IOException {
	try {
	File file = new File(servletContext.getRealPath("/") + "/"
	+ filename);
		 
	FileUtils.writeByteArrayToFile(file, image.getBytes());
	System.out.println("Go to the location:  " + file.toString()
	+ " on your computer and verify that the image has been stored.");
	} catch (IOException e) {
	throw e;
	}
	}
}
