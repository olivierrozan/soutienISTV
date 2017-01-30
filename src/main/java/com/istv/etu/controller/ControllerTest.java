package com.istv.etu.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.istv.etu.model.User;
import com.istv.etu.services.IListUsersServices;

public class ControllerTest {
	
	@Autowired
	private IListUsersServices i;
	
	@Test
	public void initTest() {
		
		assertNotNull(i);
	}
}
