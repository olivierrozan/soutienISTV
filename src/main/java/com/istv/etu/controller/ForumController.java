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
import com.istv.etu.model.form.CreateUserForm;
import com.istv.etu.services.IPostServices;
import com.istv.etu.services.ISujetsServices;

@Controller
public class ForumController {
	
	@Autowired
	private ISujetsServices SServices;
	
	@Autowired
	private IPostServices PServices;
	
	@RequestMapping(value="/forum", method = RequestMethod.GET)
    public ModelAndView forum(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null) {
			List<Sujet> sujets = SServices.getSujets();
			
			pModel.addAttribute("sujets", sujets);
			
			return new ModelAndView("forum");
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/post", method = RequestMethod.POST)
    public ModelAndView listPost(@Valid @ModelAttribute(value="creation") final Sujet s, 
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request) {

        if (!pBindingResult.hasErrors()) {
            List<Post> posts = PServices.getPosts(s.getId());
            pModel.addAttribute("posts", posts);
        	pModel.addAttribute("titreSujet", s.getTitre());
        }
        
        //return afficherListe(pModel, request);
        return new ModelAndView("post");
    }
}
