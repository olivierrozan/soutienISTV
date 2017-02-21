package com.istv.etu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.istv.etu.model.Cours;
import com.istv.etu.model.Paragraphe;
import com.istv.etu.model.Sujet;
import com.istv.etu.model.User;
import com.istv.etu.model.form.ConnexionForm;
import com.istv.etu.model.form.SearchCourseForm;
import com.istv.etu.services.ICoursesServices;
import com.istv.etu.services.IParagraphesServices;
import com.istv.etu.services.IPostServices;
import com.istv.etu.services.ISujetsServices;
import com.istv.etu.services.IThemesServices;
import com.istv.etu.services.IUsersServices;

@Controller
public class IndexController {
	
	@Autowired
    private IUsersServices service;
	
	@Autowired
    private IThemesServices serviceTheme;
	
	@Autowired
    private ICoursesServices serviceCours;
	
	@Autowired
    private IParagraphesServices servicesP;
	
	@Autowired
	private ISujetsServices servicesS;
	
	@Autowired
	private IPostServices servicesPost;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String index(final ModelMap pModel, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		
		if (request.getSession().getAttribute("uId") == null) {
			
			pModel.addAttribute("personne", "Visiteur");
            
            if (pModel.get("connexion") == null) {
                pModel.addAttribute("connexion", new ConnexionForm());
            }
            
            if (request.getSession().getAttribute("uId") != null) {
            	pModel.addAttribute("connected", "Vous êtes connecté");
    		} else {
    			pModel.addAttribute("connected", "Vous n'êtes pas connecté");
    			pModel.addAttribute("uId", null);
    		}
			
			if (pModel.get("connexion") == null) {
                pModel.addAttribute("connexion", new User());
            }
            
        	return "login";
			
		} else {
			
			return "error403";
		}
    }
	
	@RequestMapping(value="/connexionSubmit", method = RequestMethod.POST)
    public ModelAndView login(@Valid @ModelAttribute(value="connexion") final ConnexionForm pConnexion, 
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request, HttpSession sessionObj) {
		
		if (!pBindingResult.hasErrors() && service.checkLogin(pConnexion.getLogin(), pConnexion.getPassword())) {
        	sessionObj.setAttribute("uLogin", pConnexion.getLogin());
        	
        	final List<User> lListUsers = service.getUsers();
        	
        	String login = request.getSession().getAttribute("uLogin").toString();
        	String statut = service.getStatut(lListUsers, login);
        	sessionObj.setAttribute("uStatut" , statut);
        	
        	int id = service.getId(lListUsers, login);
        	sessionObj.setAttribute("uId", id);
        	
        	return new ModelAndView("redirect:/home");
        } else {       	
        	pModel.addAttribute("error", "1");
        	return new ModelAndView("redirect:/");
        }
    }
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(final ModelMap pModel, HttpServletRequest request, SessionStatus status, HttpSession sessionObj) {
        
		System.out.println("logout avant : " + request.getSession().getAttribute("uId"));
		status.setComplete();
		sessionObj.invalidate();
		System.out.println("logout apres : " + request.getSession().getAttribute("uId"));
		
        //return index(pModel, request);
		return "logout";
    }
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
    public String home(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			
			final List<Cours> listCours = serviceCours.getLastCours();
			pModel.addAttribute("cours", listCours);
			
			final List<Sujet> listSujets = servicesS.getLastSujets();
			pModel.addAttribute("sujets", listSujets);
			System.out.println("OK");
	        return "home";
			
		} else {
			System.out.println("NG");
			return "error403";
		}
    }
	
	@RequestMapping(value="/error403", method = RequestMethod.GET)
    public String error403() {
        
        return "error403";
    }
	
	@RequestMapping(value="/searchCourse", method = RequestMethod.POST)
    public String search(@Valid @ModelAttribute(value="search") final SearchCourseForm c, final ModelMap pModel, 
    		HttpServletRequest request) {
        
		if (service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			
			List<Cours> cours = serviceCours.getCoursesByName(c.getInput());
			
			List<Sujet> sujets = servicesS.getSujetsByName(c.getInput());
			
			pModel.addAttribute("cours", cours);
			pModel.addAttribute("sujets", sujets);
			pModel.addAttribute("tailleCours", cours.size());
			pModel.addAttribute("tailleSujets", sujets.size());
			pModel.addAttribute("libelleRecherche", c.getInput());
			
	        return "searchCourse";
			
		} else {
			
			return "error403";
		}
    }
	
	@RequestMapping(value="/seeSearchedCourse", method = RequestMethod.POST)
    public ModelAndView voirCours(@Valid @ModelAttribute(value="rechercheCours") final Cours c,
    		final ModelMap pModel, HttpServletRequest request) {

		if (service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			serviceCours.updateNbVues(c.getIdCours());
			
			Cours cours = serviceCours.getOneCourse(c.getIdCours());
			List<Paragraphe> paragraphes = servicesP.getParagraphes(c.getIdCours());
			
			pModel.addAttribute("cours", cours);
			pModel.addAttribute("paragraphes", paragraphes);
			pModel.addAttribute("libelle", c.getLibelleCours());
			
	        return new ModelAndView("seeSearchedCourse");
	    }else {
			return new ModelAndView("redirect:/error403");
		}
	}
}
