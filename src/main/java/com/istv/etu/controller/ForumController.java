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
import org.springframework.web.servlet.ModelAndView;

import com.istv.etu.model.Post;
import com.istv.etu.model.Sujet;
import com.istv.etu.model.Theme;
import com.istv.etu.services.IUsersServices;
import com.istv.etu.services.IPostServices;
import com.istv.etu.services.ISujetsServices;
import com.istv.etu.services.IThemesServices;

@Controller
public class ForumController {
	
	@Autowired
	private IUsersServices service;
	
	@Autowired
	private ISujetsServices SServices;
	
	@Autowired
	private IPostServices PServices;
	
	@Autowired
	private IThemesServices TServices;
	
	@RequestMapping(value="/themeForum", method = RequestMethod.GET)
    public ModelAndView themeForum(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			List<Theme> themes = TServices.getThemes();
			
			pModel.addAttribute("themes", themes);
			
			return new ModelAndView("themeForum");
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/themeForum", method = RequestMethod.POST)
    public ModelAndView addThemeForum(@Valid @ModelAttribute(value="oneTheme") final Theme t,
    		final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())
				&& !pBindingResult.hasErrors()) {
			TServices.addTheme(t.getLibelleTheme(), Integer.parseInt(request.getSession().getAttribute("uId").toString()));
			pModel.addAttribute("ok", "Le thème \'" + t.getLibelleTheme() + "\' a bien été ajouté");
			return themeForum(pModel, request);
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/sujetsForum", method = RequestMethod.POST)
    public ModelAndView sujetsForum(@Valid @ModelAttribute(value="oneTheme") final Theme t,
    		final BindingResult bindingResult, final ModelMap pModel, HttpServletRequest request) {
        		
		ModelAndView mv = null;
		
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			List<Sujet> sujets = SServices.getSujets(t.getIdTheme());
			
			pModel.addAttribute("sujets", sujets);
			pModel.addAttribute("idTheme", t.getIdTheme());
			pModel.addAttribute("libelleTheme", t.getLibelleTheme());
			
			if (request.getAttribute("sujet") == null) {
				pModel.addAttribute("sujet", new Sujet());
			}
			
			mv =  new ModelAndView("sujetsForum");
		} else {			
			mv =  new ModelAndView("redirect:/error403");			
		}
		
		return mv;
    }
	
	@RequestMapping(value="/createSujetSubmit", method = RequestMethod.POST)
    public ModelAndView createSujetSubmit(@Valid @ModelAttribute(value="sujet") final Sujet s,
    		final BindingResult bindingResult, final ModelMap pModel, HttpServletRequest request) {
        
		ModelAndView mv = null;
		
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString()) && 
				!bindingResult.hasErrors()) {			
			SServices.createSujet(s, request.getSession().getAttribute("uId").toString());
			
			mv =  themeForum(pModel, request);
		} else {
			if (bindingResult.hasErrors()) {
				Theme t = new Theme();
				t.setIdTheme(s.getFk_idTheme());
				t.setLibelleTheme(s.getTheme().getLibelleTheme());
				pModel.addAttribute("ok", "Le sujet a bien été ajouté");
				mv =  sujetsForum(t, bindingResult, pModel, request);
			}
			
			if (!service.checkStatut(request.getSession().getAttribute("uId").toString())) {
				mv =  new ModelAndView("redirect:/error403");
			}
		}
		
		return mv;
    }
	
	@RequestMapping(value="/post", method = RequestMethod.POST)
    public ModelAndView listPost(@Valid @ModelAttribute(value="onePost") final Sujet s, 
    		@Valid @ModelAttribute(value="postReply") final Post p,
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request) {

        ModelAndView mv = null;
        
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())
				&& !pBindingResult.hasErrors()) {
            if (p.getContenu() != null) {
            	PServices.addPost(p.getContenu(), s.getId(), request.getSession().getAttribute("uId").toString());
            	 pModel.addAttribute("ok", "Ton message a bien été ajouté");
            }
			    
        	List<Post> posts = PServices.getPosts(s.getId());
            
            pModel.addAttribute("posts", posts);
            pModel.addAttribute("idUser", request.getSession().getAttribute("uId"));
        	pModel.addAttribute("sujet", s);
        	pModel.addAttribute("statut", request.getSession().getAttribute("uStatut"));
        	
        	mv = new ModelAndView("post");
        }else {
        	mv = new ModelAndView("redirect:/error403");
        }
        
        return mv;
    }
	
	
}
