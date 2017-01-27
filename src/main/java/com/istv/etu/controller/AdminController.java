package com.istv.etu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.istv.etu.model.Cours;
import com.istv.etu.model.User;
import com.istv.etu.services.ICoursesServices;
import com.istv.etu.services.IThemesServices;
import com.istv.etu.services.IListUsersServices;

@Controller
public class AdminController {
	
	@Autowired
    private IListUsersServices service;
	
	@Autowired
    private IThemesServices serviceTheme;
	
	@Autowired
	private ICoursesServices serviceCours;
	
	@RequestMapping(value="/allUsers", method = RequestMethod.GET)
    public ModelAndView afficherListe(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			final List<User> lListUsers = service.getUsers();
			
			pModel.addAttribute("listUsers", lListUsers);
			
	        return new ModelAndView("listUsers");
			
		} else {
			System.out.println("erreur : " + request.getSession().getAttribute("uStatut") + ", " + request.getSession().getAttribute("uId"));
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/detailsUser", method = RequestMethod.GET)
    public ModelAndView detailsUser(@RequestParam(value="id") final Integer idUser, final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			
			final User user = service.getOneUser(idUser.toString());
			
			List<Cours> cours = user.getCours();
			
			pModel.addAttribute("user", user);
			pModel.addAttribute("cours", cours);
			
	        return new ModelAndView("detailsUser");
			
		} else {
			System.out.println("erreur : " + request.getSession().getAttribute("uStatut") + ", " + request.getSession().getAttribute("uId"));
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/allCourses", method = RequestMethod.GET)
    public ModelAndView afficherCours(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			final List<Cours> lListCours = serviceCours.getCourses();
			
			pModel.addAttribute("listCours", lListCours);
			pModel.addAttribute("idUser", request.getSession().getAttribute("uId"));
			pModel.addAttribute("idStatut", request.getSession().getAttribute("uStatut"));
			
			Date d = new Date();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sDate = s.format(d);
			
			System.out.println(sDate);
			
	        return new ModelAndView("listCourses");
			
		} else {
			System.out.println("erreur : " + request.getSession().getAttribute("uStatut") + ", " + request.getSession().getAttribute("uId"));
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/detailsCourse", method = RequestMethod.GET)
    public ModelAndView detailsCours(@RequestParam(value="id") final Integer idCourse, 
    		final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			Cours c = serviceCours.getOneCourse(idCourse.toString());
			
			pModel.addAttribute("courseDetail", c);				
	        pModel.addAttribute("dateDernModif", c.getDateDerniereModif());
					        
	        return new ModelAndView("detailsCourse");
			
		} else {
			System.out.println("erreur : " + request.getSession().getAttribute("uStatut") + ", " + request.getSession().getAttribute("uId"));
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/deleteCourse", method = RequestMethod.POST)
    public ModelAndView deleteCourse(@Valid @ModelAttribute(value="validateCourse") final Cours c, 
    		final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			serviceCours.deleteCourse(String.valueOf(c.getIdCours()));
						
	        return afficherCours(pModel, request);
			
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/validateCourse", method = RequestMethod.POST)
    public ModelAndView validerCours(@Valid @ModelAttribute(value="validateCourse") final Cours c, 
    		final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			serviceCours.validateCourse(String.valueOf(c.getIdCours()));
						
	        return afficherCours(pModel, request);
			
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
}
