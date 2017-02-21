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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.istv.etu.model.Cours;
import com.istv.etu.model.Paragraphe;
import com.istv.etu.model.Theme;
import com.istv.etu.model.form.CreateCourseForm;
import com.istv.etu.model.form.CreateParagrapheForm;
import com.istv.etu.model.form.UpdateCourseForm;
import com.istv.etu.services.ICoursesServices;
import com.istv.etu.services.IThemesServices;
import com.istv.etu.services.IUsersServices;
import com.istv.etu.services.IParagraphesServices;

@Controller
public class CoursController {
		
	@Autowired
    private IUsersServices service;
	
	@Autowired
    private ICoursesServices servicesCours;
	
	@Autowired
    private IParagraphesServices servicesP;
	
	@Autowired
	private IThemesServices servicesThemes;
    
	@RequestMapping(value="/myCourses", method = RequestMethod.GET)
    public ModelAndView myCourses(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
						
			List<Cours> cours = servicesCours.getCoursesOfOneUser(request.getSession().getAttribute("uId").toString());
						
			pModel.addAttribute("listCours", cours);
			
			return new ModelAndView("myCourses");
		} else {
			
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/addCourse", method = RequestMethod.GET)
    public ModelAndView ajouterCours(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			
			if (pModel.get("creationCours") == null) {
	            pModel.addAttribute("creationCours", new CreateCourseForm());
	        }
			
			return new ModelAndView("addCourse");
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	// TODO importer une image dans le server 
	@RequestMapping(value="/addCourse", method = RequestMethod.POST)
    public ModelAndView addCourseSubmit(@Valid @ModelAttribute(value="creationCours") final CreateCourseForm pCreation, 
            final BindingResult pBindingResult, final ModelMap pModel, 
            HttpServletRequest request, HttpSession sessionObj, @RequestParam("imageTitre") MultipartFile file) {

		ModelAndView mv = null;
		
		System.out.println("image : " + /*pCreation.getImageTitre()*/file.getOriginalFilename() + ", Titre : " + pCreation.getLibelleCours());
		pCreation.setImageTitre(file.getOriginalFilename());
		
		servicesCours.uploadImage(file, pCreation.getImageTitre());
		
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString()) 
				/*&& !pBindingResult.hasErrors()*/) {
			
			servicesCours.createCourse(pCreation.getLibelleCours(), pCreation.getImageTitre(), request.getSession().getAttribute("uId").toString());
			sessionObj.setAttribute("uLibelle", pCreation.getLibelleCours());
			System.out.println("cours ok");
			
			mv = new ModelAndView("redirect:/addCourseContent");
			
        } /*else {
        	if (pBindingResult.hasErrors()) {
        		System.out.println(pBindingResult.getFieldError());
        		mv = new ModelAndView("addCourse");
        	}       	
        }*/
        
        return mv;
    }
	
	@RequestMapping(value="/addCourseContent", method = RequestMethod.GET)
    public ModelAndView ajouterCoursContenu(final ModelMap pModel, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			
			if (pModel.get("creationCoursContenu") == null) {
				int idCours = servicesCours.getOneCourseByName(request.getSession().getAttribute("uLibelle").toString());
				
				CreateParagrapheForm c = new CreateParagrapheForm();
				c.add(new Paragraphe());
				
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
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request, @RequestParam("images[]") MultipartFile[] files) {

		
		ModelAndView mv = null;
		
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())
				/*&& !pBindingResult.hasErrors()*/) {						
			
			System.out.println("size : " + files.length);
			if (files.length > 0) {
				/*for (Paragraphe p: pCreation.getParagraphes()) {
					int order = servicesP.getOrderMax(pCreation.getFk_idCours()) + p.getOrdre();	
					
					p.setImageLocation(files.getOriginalFilename());
					servicesCours.uploadImages(files, file.getOriginalFilename());
					
					servicesP.createParagraphe(p.getTexte(), p.getImageLocation(), order, pCreation.getFk_idCours());						
				} */
				
				for (int i = 0; i < files.length; i++) {
					int order = servicesP.getOrderMax(pCreation.getFk_idCours()) + pCreation.getParagraphes().get(i).getOrdre();	
					System.out.println("paragraphes ctrl : " + files[i].getOriginalFilename());
					pCreation.getParagraphes().get(i).setImageLocation(files[i].getOriginalFilename());
					servicesCours.uploadImages(files);
					
					servicesP.createParagraphe(pCreation.getParagraphes().get(i).getTexte(), pCreation.getParagraphes().get(i).getImageLocation(), order, pCreation.getFk_idCours());
				}
			}
			
			mv = new ModelAndView("redirect:/myCourses");
        } else {
        	System.out.println("Erreur de création de cours");
        	mv = new ModelAndView("addContent");
        }
        
        return mv;
    }
	
	@RequestMapping(value="/themeCourses", method = RequestMethod.GET)
    public ModelAndView themeCourses(final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			List<Theme> themes = servicesThemes.getThemes();
			
			pModel.addAttribute("themes", themes);
			
			return new ModelAndView("themeCours");
		} else {
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/cours", method = RequestMethod.POST)
    public ModelAndView cours(@Valid @ModelAttribute(value="coursesByThemeForm") final Theme t,
    		final BindingResult bindingResult, final ModelMap pModel, HttpServletRequest request) {

		ModelAndView mv = null;
		
		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString()) && !bindingResult.hasErrors()) {
			
			List<Cours> cours = servicesCours.getCoursesByTheme(t.getIdTheme());
			
			pModel.addAttribute("cours", cours);
			pModel.addAttribute("libelleTheme", t.getLibelleTheme());
			
	        mv =  new ModelAndView("coursesByTheme");
	    }else {
			if (bindingResult.hasErrors()) {
				return new ModelAndView("themeCours");
			}
			
			if (!service.checkStatut(request.getSession().getAttribute("uId").toString())) {
				mv =  new ModelAndView("redirect:/error403");
			}
	    	
		}
		
		return mv;
	}
	
	@RequestMapping(value="/seeCourse", method = RequestMethod.POST)
    public ModelAndView voirCours(@Valid @ModelAttribute(value="creationCoursContenu") final Cours c,
    		final ModelMap pModel, HttpServletRequest request) {

		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			pModel.addAttribute("libelle", c.getLibelleCours());
			
			List<Paragraphe> paragraphes = servicesP.getParagraphes(c.getIdCours());
			
			pModel.addAttribute("paragraphes", paragraphes);
			
	        return new ModelAndView("seeCourse");
	    }else {
			return new ModelAndView("redirect:/error403");
		}
	}
	
	@RequestMapping(value="/updateCourse", method = RequestMethod.POST)
    public ModelAndView updateCourse(@Valid @ModelAttribute(value="upCours") final Cours c,
    		final ModelMap pModel, HttpServletRequest request) {

		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			
			if (pModel.get("updateCourseForm") == null && pModel.get("updateParagrapheForm") == null && pModel.get("creationCoursContenu") == null) {
                Cours cours = servicesCours.getOneCourse(c.getIdCours());
                
				pModel.addAttribute("updateCourseForm", new UpdateCourseForm());
                List<Paragraphe> paragraphes = servicesP.getParagraphes(c.getIdCours());
                
                CreateParagrapheForm cp = new CreateParagrapheForm();
				cp.add(new Paragraphe());
				                
                pModel.addAttribute("creationCoursContenu", cp);
    			
                pModel.addAttribute("cours", cours);
    			pModel.addAttribute("paragraphes", paragraphes);
                
            }
						
	        return new ModelAndView("updateCourseForm");
	    }else {
			return new ModelAndView("redirect:/error403");
		}
	}
	
	@RequestMapping(value="/updateCourseSubmit", method = RequestMethod.POST)
    public ModelAndView updateCourseSubmit(@Valid @ModelAttribute(value="updateCourseForm") final UpdateCourseForm c,
    		final ModelMap pModel, HttpServletRequest request) {

		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			
			
			servicesCours.updateCourse(c);
						
	        return new ModelAndView("redirect:/myCourses");
	    }else {
			return new ModelAndView("redirect:/error403");
		}
	}
	
	@RequestMapping(value="/deleteCourse", method = RequestMethod.POST)
    public ModelAndView deleteCourse(@Valid @ModelAttribute(value="delCours") final Cours c,
    		final ModelMap pModel, HttpServletRequest request) {

		if (request.getSession().getAttribute("uId") != null && service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			
			servicesCours.deleteCourse(c.getIdCours());
						
	        return new ModelAndView("redirect:/myCourses");
	    }else {
			return new ModelAndView("redirect:/error403");
		}
	}
}
