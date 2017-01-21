package com.istv.etu.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.istv.etu.model.Cours;
import com.istv.etu.model.User;
import com.istv.etu.services.IListCoursesServices;
import com.istv.etu.services.IListThemesServices;
import com.istv.etu.services.IListUsersServices;

@Controller
public class AdminController {
	
	@Autowired
    private IListUsersServices service;
	
	@Autowired
    private IListThemesServices serviceTheme;
	
	@Autowired
	private IListCoursesServices serviceCours;
	
	@RequestMapping(value="/allUsers", method = RequestMethod.GET)
    public ModelAndView afficherListe(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			final List<User> lListUsers = service.getUsers();
			pModel.addAttribute("listUsers", lListUsers);
			
			/*System.out.println("Users : ");
			for(User u: lListUsers) {
				System.out.println("id : " + u.getId() + " : " + u.getLogin());
				Set<Cours> c = u.getCours();
				
				for (Cours co: c) {
					System.out.println("id : " + co.getIdCours() + " : " + co.getLibelleCours());
				}
			}*/
			
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
			
	        return new ModelAndView("listCourses");
			
		} else {
			System.out.println("erreur : " + request.getSession().getAttribute("uStatut") + ", " + request.getSession().getAttribute("uId"));
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/detailsCourse", method = RequestMethod.GET)
    public ModelAndView detailsCours(@RequestParam(value="id") final Integer idCourse, final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			Cours c = serviceCours.getOneCourse(idCourse.toString());
			
			SimpleDateFormat formatter = new SimpleDateFormat("EEEE d MMMM yyyy HH:mm:ss");
						
			pModel.addAttribute("courseDetail", c);				
			pModel.addAttribute("courseWriter", c);
	        pModel.addAttribute("dateDernModif", formatter.format(c.getDateDerniereModif()));
					        
	        return new ModelAndView("detailsCourse");
			
		} else {
			System.out.println("erreur : " + request.getSession().getAttribute("uStatut") + ", " + request.getSession().getAttribute("uId"));
			return new ModelAndView("redirect:/error403");
		}
    }
}
