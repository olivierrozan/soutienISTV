package com.istv.etu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.istv.etu.model.Cours;
import com.istv.etu.model.Post;
import com.istv.etu.model.Sujet;
import com.istv.etu.model.Theme;
import com.istv.etu.model.User;
import com.istv.etu.services.ICoursesServices;
import com.istv.etu.services.IPostServices;
import com.istv.etu.services.IThemesServices;
import com.istv.etu.services.IUsersServices;

@Controller
public class AdminController {
	
	@Autowired
    private IUsersServices service;
	
	@Autowired
    private IThemesServices serviceTheme;
	
	@Autowired
	private ICoursesServices serviceCours;
	
	@Autowired
	private IPostServices servicePost;
	
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
			
			if (user.getCours().size() > 0) {				
				pModel.addAttribute("user", user);
				pModel.addAttribute("cours", cours);
			} else {				
				User userWithoutCourses = service.getOneUserWithoutCourses(idUser.toString());
				
				pModel.addAttribute("user", userWithoutCourses);
			}
			
			
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
			final List<Theme> listeThemes = serviceTheme.getThemes();
			
			pModel.addAttribute("listCours", lListCours);
			pModel.addAttribute("listThemes", listeThemes);
			pModel.addAttribute("idUser", request.getSession().getAttribute("uId"));
			pModel.addAttribute("idStatut", request.getSession().getAttribute("uStatut"));
			
			/*Date d = new Date();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sDate = s.format(d);*/
						
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
			
			Cours c = serviceCours.getOneCourse(idCourse);
			
			pModel.addAttribute("courseDetail", c);				
	        pModel.addAttribute("paragraphes", c.getParagraphes());
					        
	        return new ModelAndView("detailsCourse");
			
		} else {
			System.out.println("erreur : " + request.getSession().getAttribute("uStatut") + ", " + request.getSession().getAttribute("uId"));
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/validateCourse", method = RequestMethod.POST)
    public ModelAndView validerCours(@Valid @ModelAttribute(value="validateCourse") final Cours c, 
    		final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			
			serviceCours.validateCourse(c.getIdCours(), c.getTheme().getIdTheme());
						
	        //return afficherCours(pModel, request);
			return new ModelAndView("redirect:/allCourses");
			
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/deactivateCourse", method = RequestMethod.POST)
    public ModelAndView desactiverCours(@Valid @ModelAttribute(value="deactivateCourse") final Cours c, 
    		final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			
			serviceCours.deactivateCourse(c.getIdCours());
						
	        //return afficherCours(pModel, request);
			return new ModelAndView("redirect:/allCourses");
			
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/banUser", method = RequestMethod.POST)
    public ModelAndView banUser(@Valid @ModelAttribute(value="ban") final User u, 
    		final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			service.banUser(u.getId(), u.getStatut());
						
	        return afficherListe(pModel, request);
			
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/allForum", method = RequestMethod.GET)
    public ModelAndView allForum(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && request.getSession().getAttribute("uStatut").equals("admin")) {
			List<Theme> themes = serviceTheme.getThemes();
			
			pModel.addAttribute("themes", themes);
	        return new ModelAndView("allForum");
			
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/postResolu", method = RequestMethod.POST)
    public ModelAndView postResolu(@Valid @ModelAttribute(value="resolu") final Sujet s, 
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request) {

        ModelAndView mv = null;
        
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString()) && 
				request.getSession().getAttribute("uStatut").equals("admin")) {
            
			servicePost.postResolu(s.getId());
			
        	mv = new ModelAndView("redirect:/themeForum");
        }else {
        	mv = new ModelAndView("redirect:/error403");
        }
        
        return mv;
    }
}
