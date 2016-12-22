package com.istv.etu.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.istv.etu.model.User;
import com.istv.etu.model.form.CreateUserForm;
import com.istv.etu.model.UpdateUser;
import com.istv.etu.model.form.UpdateUserForm;
import com.istv.etu.services.IListUsersServices;

@Controller
public class CreateUserController {
	
	@Autowired
    private IListUsersServices service;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
    public String afficherListe(final ModelMap pModel) {
		
		final List<User> lListUsers = service.getUsers();
		pModel.addAttribute("listUsers", lListUsers);
        return "listUsers";
    }

    @RequestMapping(value="/createUserForm", method = RequestMethod.GET)
    public String creerForm(final ModelMap pModel) {
        
    	final List<User> lListUsers = service.getUsers();
        pModel.addAttribute("listUsers", lListUsers);
        
        if (pModel.get("creation") == null) {
            pModel.addAttribute("creation", new CreateUserForm());
        }
        
        return "createUsersForm";
    }

    @RequestMapping(value="/createUserSubmit", method = RequestMethod.POST)
    public String creerSubmit(@Valid @ModelAttribute(value="creation") final CreateUserForm pCreation, 
            final BindingResult pBindingResult, final ModelMap pModel) {

        if (!pBindingResult.hasErrors()) {
            service.createUser(pCreation.getNom(), pCreation.getPrenom());
        }
        
        return afficherListe(pModel);
    }
    
    @RequestMapping(value="/deleteUser", method = RequestMethod.GET)
    public String supprimer(@RequestParam(value="id") final Integer pIdCourse, final ModelMap pModel) {

        service.deleteUser(pIdCourse);
        return afficherListe(pModel);
    }
    
    @RequestMapping(value="/listUpdateUser", method = RequestMethod.GET)
    public String updateUserForm(final ModelMap pModel) {
        
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
    public String modifier(@Valid @ModelAttribute(value="modification") final UpdateUserForm pModification, 
            final BindingResult pBindingResult, final ModelMap pModel) {

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

        return afficherListe(pModel);
    }
}
