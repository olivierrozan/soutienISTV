package com.istv.etu.controller;

import java.util.LinkedList;
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

import com.istv.etu.model.User;
import com.istv.etu.model.form.CreateUserForm;
import com.istv.etu.model.UpdateUser;
import com.istv.etu.model.form.UpdateUserForm;
import com.istv.etu.services.IListUsersServices;

@Controller
public class UserController {
	
	@Autowired
    private IListUsersServices service;
	
	@RequestMapping(value="/profil", method = RequestMethod.GET)
    public ModelAndView detailsUser(@RequestParam(value="id") final Integer id, final ModelMap pModel, HttpServletRequest request) {
        
		if (request.getSession().getAttribute("uId") != null) {
			
			final User user = service.getOneUser(id.toString());
			pModel.addAttribute("user", user);
			
	        return new ModelAndView("profil");
			
		} else {
			System.out.println("erreur : " + request.getSession().getAttribute("uStatut") + ", " + request.getSession().getAttribute("uId"));
			return new ModelAndView("redirect:/error403");
		}
    }
	
	@RequestMapping(value="/createUserForm", method = RequestMethod.GET)
    public ModelAndView creerForm(final ModelMap pModel, HttpServletRequest request) {
        
    	final List<User> lListUsers = service.getUsers();
        pModel.addAttribute("listUsers", lListUsers);
        
        if (pModel.get("creation") == null) {
            pModel.addAttribute("creation", new CreateUserForm());
        }
        
        return new ModelAndView("createUsersForm");
    }

    @RequestMapping(value="/createUserForm", method = RequestMethod.POST)
    public ModelAndView creerSubmit(@Valid @ModelAttribute(value="creation") final CreateUserForm pCreation, 
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request) {

        if (!pBindingResult.hasErrors()) {
            service.createUser(pCreation.getNom(), pCreation.getPrenom(), pCreation.getLogin(), pCreation.getPassword(), pCreation.getFormation());
        }
        
        //return afficherListe(pModel, request);
        return new ModelAndView("redirect:/");
    }
    
    @RequestMapping(value="/deleteUser", method = RequestMethod.GET)
    public ModelAndView supprimer(@RequestParam(value="id") final Integer pIdCourse, final ModelMap pModel, HttpServletRequest request) {

        service.deleteUser(pIdCourse);
        
        //return afficherListe(pModel, request);
        return new ModelAndView("redirect:/");
    }
    
    @RequestMapping(value="/updateUser", method = RequestMethod.GET)
    public String updateUserForm(final ModelMap pModel, HttpServletRequest request) {
        
    	if (pModel.get("modification") == null) {
            final List<User> lListUsers = service.getUsers();
            final UpdateUserForm lModificationForm = new UpdateUserForm();
            final List<UpdateUser> lListe = new LinkedList<UpdateUser>();
            
            for (final User lUser : lListUsers) {
                final UpdateUser lModificationUser = new UpdateUser();
                lModificationUser.setId(lUser.getId());
                lModificationUser.setNom(lUser.getNom());
                lModificationUser.setPrenom(lUser.getPrenom());
                lListe.add(lModificationUser);
            }
            
            lModificationForm.setListUsers(lListe);
            pModel.addAttribute("modification", lModificationForm);
        }
        
        return "updateUserForm";
    }

    @RequestMapping(value="/updateUserSubmit", method = RequestMethod.POST)
    public ModelAndView modifier(@Valid @ModelAttribute(value="modification") final UpdateUserForm pModification, 
            final BindingResult pBindingResult, final ModelMap pModel, HttpServletRequest request) {

        if (!pBindingResult.hasErrors()) {
            final List<User> lListUsers= new LinkedList<User>();
            
            for (final UpdateUser lModificationCourse : pModification.getListUsers()) {
                final User lUser = new User();
                lUser.setId(lModificationCourse.getId());
                lUser.setNom(lModificationCourse.getNom());
                lUser.setPrenom(lModificationCourse.getPrenom());
                lListUsers.add(lUser);
            }
            
            service.updateUser(lListUsers);
        }

        //return afficherListe(pModel, request);
        return new ModelAndView("redirect:/");
    }
}
