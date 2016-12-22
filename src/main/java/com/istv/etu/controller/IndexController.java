package com.istv.etu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.istv.etu.model.form.ConnexionForm;
import com.istv.etu.services.IListUsersServices;

@Controller

public class IndexController {
	
	@Autowired
    private IListUsersServices service;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String index(final ModelMap pModel, HttpServletRequest request) {
        pModel.addAttribute("personne", "Visiteur");
        
        System.out.println("logout : " + request.getSession().getAttribute("uid"));
        if (pModel.get("connexion") == null) {
            pModel.addAttribute("connexion", new ConnexionForm());
        }
        
        if (request.getSession().getAttribute("uid") != null) {
        	pModel.addAttribute("connected", "Vous êtes connecté");
		} else {
			pModel.addAttribute("connected", "Vous n'êtes pas connecté");
		}
        
        return "index";
    }
	
	@RequestMapping(value="/connexionSubmit", method = RequestMethod.POST)
    public String creerSubmit(@Valid @ModelAttribute(value="connexion") final ConnexionForm pConnexion, 
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request, HttpSession sessionObj) {

        if (!pBindingResult.hasErrors()) {
        	sessionObj.setAttribute("uid" , pConnexion.getLogin());
        	
        	//System.out.println(pModel.get("uid"));
        	return news(pModel, request);
        } else {
        	return index(pModel, request);
        }
    }
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(final ModelMap pModel, HttpServletRequest request, SessionStatus status, HttpSession sessionObj) {
        
		System.out.println("logout : " + request.getSession().getAttribute("uid"));
		status.setComplete();
		sessionObj.invalidate();
        
        return index(pModel, request);
    }
	
	@RequestMapping(value="/news", method = RequestMethod.GET)
    public String news(final ModelMap pModel, HttpServletRequest request) {
        
		System.out.println("news : " + request.getSession().getAttribute("uid"));
		if (request.getSession().getAttribute("uid") != null) {
			pModel.addAttribute("connected", "Vous êtes connecté");
			
			return "news";
		} else {
			return error403();
		}
    }
	
	@RequestMapping(value="/news2", method = RequestMethod.GET)
    public String news2(final ModelMap pModel, HttpServletRequest request) {
        
		System.out.println("news2 : " + request.getSession().getAttribute("uid"));
		if (request.getSession().getAttribute("uid") != null) {
			pModel.addAttribute("connected", "Vous êtes connecté");
			
			return "news2";
		} else {
			return error403();
		}
    }
	
	@RequestMapping(value="/error403", method = RequestMethod.GET)
    public String error403() {
        
        return "error403";
    }
}
