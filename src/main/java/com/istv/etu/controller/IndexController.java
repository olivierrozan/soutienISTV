package com.istv.etu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.istv.etu.model.Theme;
import com.istv.etu.model.User;
import com.istv.etu.model.form.ConnexionForm;
import com.istv.etu.services.IThemesServices;
import com.istv.etu.services.IListUsersServices;

@Controller
public class IndexController {
	
	@Autowired
    private IListUsersServices service;
	
	@Autowired
    private IThemesServices serviceTheme;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String index(final ModelMap pModel, HttpServletRequest request) {
		
		
		if (request.getSession().getAttribute("uId") == null) {
			
        	pModel.addAttribute("personne", "Visiteur");
            
            //System.out.println("Id : " + request.getSession().getAttribute("uId"));
            if (pModel.get("connexion") == null) {
                pModel.addAttribute("connexion", new ConnexionForm());
            }
            
            if (request.getSession().getAttribute("uId") != null) {
            	pModel.addAttribute("connected", "Vous êtes connecté");
    		} else {
    			pModel.addAttribute("connected", "Vous n'êtes pas connecté");
    			pModel.addAttribute("uId", null);
    		}
            
        	return "login";
			
		} else {
			
			return "error403";
		}
    }
	
	@RequestMapping(value="/connexionSubmit", method = RequestMethod.POST)
    public ModelAndView creerSubmit(@Valid @ModelAttribute(value="connexion") final ConnexionForm pConnexion, 
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request, HttpSession sessionObj) {
		
		if (!pBindingResult.hasErrors() && service.checkAuth(pConnexion.getLogin(), pConnexion.getPassword())) {
        	sessionObj.setAttribute("uLogin", pConnexion.getLogin());
        	
        	final List<User> lListUsers = service.getUsers();
        	
        	String login = request.getSession().getAttribute("uLogin").toString();
        	String statut = service.getStatut(lListUsers, login);
        	sessionObj.setAttribute("uStatut" , statut);
        	
        	int id = service.getId(lListUsers, login);
        	sessionObj.setAttribute("uId", id);
        	
        	System.out.println("Connecté : " + statut);
        	System.out.println("Id : " + id);
        	System.out.println("Login : " + login);
        	System.out.println("Statut : " + statut);
        	
    		//pModel.addAttribute("listUsers", lListUsers);
        	        	
        	return new ModelAndView("redirect:/home");
        } else {
        	//String param = request.getParameter("error");
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
        
		if (request.getSession().getAttribute("uId") != null) {
			final List<User> lListUsers = service.getUsers();
			pModel.addAttribute("listUsers", lListUsers);
			
			final List<Theme> lListThemes = serviceTheme.getThemes();
			pModel.addAttribute("listThemes", lListThemes);
			
	        return "home";
			
		} else {
			
			return "error403";
		}
    }
	
	@RequestMapping(value="/error403", method = RequestMethod.GET)
    public String error403() {
        
        return "error403";
    }
}
