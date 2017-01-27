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
import org.springframework.web.servlet.ModelAndView;

import com.istv.etu.model.Cours;
import com.istv.etu.model.Image;
import com.istv.etu.model.Paragraphe;
import com.istv.etu.model.User;
import com.istv.etu.model.form.CreateParagrapheForm;
import com.istv.etu.services.IImagesServices;
import com.istv.etu.services.ICoursesServices;
import com.istv.etu.services.IListUsersServices;
import com.istv.etu.services.IParagraphesServices;

@Controller
public class CoursController {
	
	//private static final Logger logger = Logger.getLogger(CoursController.class);
	
	@Autowired
    private IListUsersServices service;
	
	@Autowired
    private ICoursesServices servicesCours;
	
	@Autowired
    private IParagraphesServices servicesP;
	
	@Autowired
	private IImagesServices servicesI;
    
	@RequestMapping(value="/myCourses", method = RequestMethod.GET)
    public ModelAndView myCourses(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null) {
			
			final User user = service.getOneUser(request.getSession().getAttribute("uId").toString());
			
			List<Cours> cours = user.getCours();
			
			pModel.addAttribute("user", user);
			pModel.addAttribute("cours", cours);
			
			return new ModelAndView("myCourses");
		} else {
			System.out.println("erreur : " + request.getSession().getAttribute("uStatut") + ", " + request.getSession().getAttribute("uId"));
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/addCourse", method = RequestMethod.GET)
    public ModelAndView ajouterCours(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null) {
			
			if (pModel.get("creationCours") == null) {
	            pModel.addAttribute("creationCours", new Cours());
	        }
			
			return new ModelAndView("addCourse");
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	// TODO importer une image dans le server 
	@RequestMapping(value="/addCourse", method = RequestMethod.POST)
    public ModelAndView creerSubmit(@Valid @ModelAttribute(value="creationCours") final Cours pCreation, 
            final BindingResult pBindingResult, final ModelMap pModel, 
            HttpServletRequest request, HttpSession sessionObj) {

		boolean error = false;
		
		if (pCreation.getLibelleCours().isEmpty()) {
			pBindingResult.rejectValue("libelleCours", "NotEmpty.creation.login");
		    error = true;
		}
		System.out.println("image : " + pCreation.getImageTitre() + ", Titre : " + pCreation.getLibelleCours());
		if (request.getSession().getAttribute("uId") != null && !pBindingResult.hasErrors() && !error) {
			
			servicesCours.createCourse(pCreation.getLibelleCours(), pCreation.getImageTitre(), request.getSession().getAttribute("uId").toString());
			sessionObj.setAttribute("uLibelle", pCreation.getLibelleCours());
			
        } else {
        	System.out.println("error");
        }
        
        //return ajouterCoursContenu(pModel, request);
        return new ModelAndView("redirect:/addCourseContent");
    }
	
	@RequestMapping(value="/addCourseContent", method = RequestMethod.GET)
    public ModelAndView ajouterCoursContenu(final ModelMap pModel, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("uId") != null) {
			
			if (pModel.get("creationCoursContenu") == null) {
				int idCours = servicesCours.getOneCourseByName(request.getSession().getAttribute("uLibelle").toString());
				//System.out.println("id : " + idCours);
				CreateParagrapheForm c = new CreateParagrapheForm();
				c.add(new Paragraphe());
				c.add(new Image());
				
				pModel.addAttribute("creationCoursContenu", c);
				pModel.addAttribute("idCours", idCours);
	        }
			
			return new ModelAndView("addContent");
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/addCourseContent", method = RequestMethod.POST)
    public ModelAndView ajouterCoursContenuSubmit(@Valid @ModelAttribute(value="creationCoursContenu") final CreateParagrapheForm pCreation, 
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request) {

		if (request.getSession().getAttribute("uId") != null && !pBindingResult.hasErrors()) {			
			
			if (pCreation.getParagraphes().size() > 0) {
				for (Paragraphe p: pCreation.getParagraphes()) {
					//if (p.getTexte() != null && p.getOrdre() != 0) {
					System.out.println("CTRL image : " + p.getImageLocation());
						servicesP.createParagraphe(p.getTexte(), p.getImageLocation(), p.getOrdre(), pCreation.getFk_idCours());
					//}					
				}
			}
			
			/*if (pCreation.getImages().size() > 0) {
				for (Image p: pCreation.getImages()) {
					if (p.getLocation() != null && p.getOrdre() != 0) {
						System.out.println("img : " + p.getOrdre() + " : " + p.getLocation() + ", " + pCreation.getFk_idCours());
						servicesI.createImage(p.getLocation(), p.getOrdre(), pCreation.getFk_idCours());
					}					
				}
			}*/			
						
        } else {
        	System.out.println("error");
        }
        
        //return ajouterCours(pModel, request);
        return new ModelAndView("redirect:/home");
    }
	
	// TODO afficher les images et les paragraphes dans l'ordre
	@RequestMapping(value="/seeCourse", method = RequestMethod.POST)
    public ModelAndView voirCours(@Valid @ModelAttribute(value="creationCoursContenu") final Cours c,
    		final ModelMap pModel, HttpServletRequest request) {

		if (request.getSession().getAttribute("uId") != null) {
			pModel.addAttribute("libelle", c.getLibelleCours());
			
			List<Paragraphe> paragraphes = servicesP.getParagraphes(c.getIdCours());
			
			pModel.addAttribute("paragraphes", paragraphes);
			
			/*List<Image> images = servicesI.getImages(c.getIdCours());			
			pModel.addAttribute("images", images);*/
			
	        return new ModelAndView("seeCourse");
	    }else {
			return new ModelAndView("redirect:/error403");
		}
	}
}
