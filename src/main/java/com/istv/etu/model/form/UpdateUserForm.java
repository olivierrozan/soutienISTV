package com.istv.etu.model.form;

import java.util.List;

import javax.validation.Valid;

import com.istv.etu.model.UpdateUser;

public class UpdateUserForm {
	
	@Valid
    private List<UpdateUser> listUser;

    public void setListUsers(final List<UpdateUser> pUser) {
    	listUser = pUser;
    }

    public List<UpdateUser> getListUsers() {
        return listUser;
    }
}
