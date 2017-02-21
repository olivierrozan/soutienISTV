package com.istv.etu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.istv.etu.model.Cours;
import com.istv.etu.model.form.UpdateParagrapheForm;
import com.istv.etu.services.ICoursesServices;
import com.istv.etu.services.IParagraphesServices;
import com.istv.etu.services.IUsersServices;

@Controller
public class ParagrapheController {
	
	@Autowired
    private IUsersServices service;
	
	@Autowired
    private ICoursesServices servicesCours;
	
	@Autowired
    private IParagraphesServices servicesP;
	
	@RequestMapping(value="/updateParagrapheSubmit", method = RequestMethod.POST)
    public ModelAndView updateParagrapheSubmit(@Valid @ModelAttribute(value="updateParagrapheForm") final UpdateParagrapheForm c,
    		final ModelMap pModel, HttpServletRequest request, @RequestParam("imagelocation") MultipartFile file) {

		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			System.out.println("id : " + c.getIdParagraphe());
			System.out.println("texte : " + c.getTexte());
			System.out.println("img : " + c.getImageLocation());
			
			c.setImageLocation(file.getOriginalFilename());
			servicesCours.uploadImage(file, file.getOriginalFilename());
			
			servicesP.updateParagraphe(c);
						
	        return new ModelAndView("redirect:/myCourses");
	    }else {
			return new ModelAndView("redirect:/error403");
		}
	}
	
	@RequestMapping(value="/deleteParagraphe", method = RequestMethod.POST)
    public ModelAndView deleteParagraphe(@Valid @ModelAttribute(value="delCours") final Cours c,
    		final ModelMap pModel, HttpServletRequest request) {

		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			
			servicesCours.deleteCourse(c.getIdCours());
						
	        return new ModelAndView("redirect:/myCourses");
	    }else {
			return new ModelAndView("redirect:/error403");
		}
	}
}
