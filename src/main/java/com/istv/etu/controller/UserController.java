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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.istv.etu.model.Cours;
import com.istv.etu.model.User;
import com.istv.etu.model.form.CreateUserForm;
import com.istv.etu.model.form.UpdatePasswordForm;
import com.istv.etu.model.form.UpdateUserForm;
import com.istv.etu.services.ICoursesServices;
import com.istv.etu.services.IUsersServices;

@Controller
public class UserController {
	
	@Autowired
    private IUsersServices service;
	
	@Autowired
    private ICoursesServices serviceCours;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@RequestMapping(value="/profil", method = RequestMethod.GET)
    public ModelAndView profil(final ModelMap pModel, HttpServletRequest request) {
        		
		if (service.checkStatut(request.getSession().getAttribute("uId").toString())) {
			
			final User user = service.getOneUserWithoutCourses(request.getSession().getAttribute("uId").toString());
			final List<Cours> cours = serviceCours.getCoursesOfOneUser(request.getSession().getAttribute("uId").toString());
			
			pModel.addAttribute("user", user);
			
			if (cours.size() != 0) {
				pModel.addAttribute("cours", cours);
			} else {
				pModel.addAttribute("no", "Aucun cours");
			}
			
			
	        return new ModelAndView("profil");
			
		} else {
			System.out.println("erreur : " + request.getSession().getAttribute("uStatut") + ", " + request.getSession().getAttribute("uId"));
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/createUserForm", method = RequestMethod.GET)
    public ModelAndView creerForm(final ModelMap pModel, HttpServletRequest request) {
        
        if (pModel.get("creation") == null) {
            pModel.addAttribute("creation", new CreateUserForm());
        }
        
        return new ModelAndView("createUserForm");
    }

    @RequestMapping(value="/createUserForm", method = RequestMethod.POST)
    public ModelAndView creerSubmit(@Valid @ModelAttribute(value="creation") final CreateUserForm pCreation, 
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request) {

    	ModelAndView mv = null;
    	
    	if (!pBindingResult.hasErrors()) {
    		if (service.createUser(pCreation.getNom(), pCreation.getPrenom(), pCreation.getLogin(), pCreation.getEmail(), pCreation.getPassword(), pCreation.getPassword2(), pCreation.getFormation())) {
    			
    			// takes input from e-mail form
    	        String recipientAddress = pCreation.getEmail();
    	        String subject = "Soutien ISTV : Inscription";
    	        String message = "Bienvenue au soutien scolaire du campus du Mont Houy!\n\n";
    			message += "Ton identifiant est : " + pCreation.getLogin() + ".\n";
    	        message += "Ton mot de passe est : " + pCreation.getPassword() + ".\n\n";
    	        message += "Ces informations sont à conserver. Ne les perds pas!\n\n";
    	        message += "Pour te connecter, clique sur le lien suivant : \n\n";
    	        message += "Pour te connecter, clique sur le lien suivant : \n\n";
    	        message += "http://localhost:8080/soutienISTV/";
    	        message += "Merci et à bientôt.\n\n";
    	        message += "l'équipe SoutienISTV";
    	        
    			service.sendEmail(mailSender, recipientAddress, subject, message);
    			
    			pModel.addAttribute("ok", "Inscription réussie. Un mail a été envoyée à l'adresse électronique : " + pCreation.getEmail() + ".");
    			mv = new ModelAndView("createUserForm");
    		} else {
    			pModel.addAttribute("NotMatch", "Les mots de passe ne correspondent pas");
    			mv = new ModelAndView("createUserForm");
    		}    		
    	} else {
    		
    		mv = new ModelAndView("createUserForm");
    	}
        
        return mv;
    }
    
    @RequestMapping(value="/updateUser", method = RequestMethod.GET)
    public String updateUserForm(final ModelMap pModel, HttpServletRequest request) {
        
    	if (pModel.get("modification") == null 
    			&& service.checkStatut(request.getSession().getAttribute("uId").toString())) {
            final User user = service.getOneUserWithoutCourses(request.getSession().getAttribute("uId").toString());
            
            pModel.addAttribute("modification", new UpdateUserForm());
            pModel.addAttribute("user", user);
            pModel.addAttribute("uId", request.getSession().getAttribute("uId"));
        }
        
        return "updateUserForm";
    }

    @RequestMapping(value="/updateUserSubmit", method = RequestMethod.POST)
    public ModelAndView modifier(@Valid @ModelAttribute(value="modification") final UpdateUserForm pModification, 
            final BindingResult pBindingResult, final ModelMap pModel, 
            HttpServletRequest request, @RequestParam("avatar") MultipartFile file) {

        ModelAndView mv = null;
    	
    	if (/*!pBindingResult.hasErrors() 
    			&& */service.checkStatut(request.getSession().getAttribute("uId").toString())) {            
            
    		pModification.setAvatar(file.getOriginalFilename());
    		service.uploadImage(file, pModification.getAvatar());
    		service.updateUser(pModification, request.getSession().getAttribute("uId").toString());
            
            pModel.addAttribute("ok", "Ton profil a été modifié");
            //mv = new ModelAndView("profil");
            mv = profil(pModel, request);
        } else {
        	/*if (pBindingResult.hasErrors()) {
        		mv = new ModelAndView("updateUserForm");
        	}*/
        	
        	if (request.getSession().getAttribute("uId") == null) {
        		mv = new ModelAndView("redirect:/error403");
        	}
        }
    	
    	return mv;
    }
    
    @RequestMapping(value="/updatePassword", method = RequestMethod.GET)
    public ModelAndView modifierMDPForm(final ModelMap pModel, HttpServletRequest request) {

        if (service.checkStatut(request.getSession().getAttribute("uId").toString())) {
            
        	if (pModel.get("updatePWD") == null) {
                pModel.addAttribute("updatePWD", new UpdatePasswordForm());
            }
        	
            return new ModelAndView("/updatePassword");
        } else {
        	return new ModelAndView("redirect:/error403");
        }        
    }
    
    @RequestMapping(value="/updatePasswordSubmit", method = RequestMethod.POST)
    public ModelAndView modifierMDP(@Valid @ModelAttribute(value="updatePWD") final UpdatePasswordForm u, 
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request) {

        String error = "";
        ModelAndView mv = null;
    	
    	if (!pBindingResult.hasErrors() 
    			&& service.checkStatut(request.getSession().getAttribute("uId").toString())) {
            
            error = service.updatePassword(u.getOldPassword(), u.getNewPassword(), u.getNewPassword2(), request.getSession().getAttribute("uId").toString());
            
            if (!error.equals("ok")) {
            	System.out.println("CTRL : " + error);
            	pModel.addAttribute("error", error);
            	
            	mv = new ModelAndView("updatePassword");
            } else {
            	pModel.addAttribute("ok", "Ton mot de passe a bien été modifié");
            	
            	mv = profil(pModel, request);
            }
        }       	
        	if (pBindingResult.hasErrors()) {        		
        		mv = new ModelAndView("updatePassword");
        	}
        	
        	if (request.getSession().getAttribute("uId") == null) {
        		mv = new ModelAndView("redirect:/error403");
        	}
        	    	
    	return mv;
    }
}
