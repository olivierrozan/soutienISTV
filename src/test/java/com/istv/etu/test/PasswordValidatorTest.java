package com.istv.etu.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.testng.Assert;

public class PasswordValidatorTest {
		
	private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16})";

    public PasswordValidatorTest(){
    	pattern = Pattern.compile(PASSWORD_PATTERN);
    }
  
    /**
     * Validate password with regular expression
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public boolean validate(final String password){
	    matcher = pattern.matcher(password);
	    return matcher.matches();
    }

	@Test
	public void ValidPasswordTest() {
		
		String[] password = new String[] {
				"mkyong1A@", "mkYOn12$", "adminPWD59&", "adminPWD59.|]"
	   	};
	   
		for (String temp : password) {
			boolean valid = this.validate(temp);
			System.out.println("Password is valid : " + temp + " , " + valid);
			Assert.assertEquals(true, valid);
	    }
	
	}

	@Test
	public void InValidPasswordTest() {

		String[] password = new String[] {
				"amdinpwdpwd", "ADMINPWDPWD", "123456789", "ADMINPWD59", "adminpwd59", "adminPWDPWD", "aP59&", "adminadminPWDPWD5959", "аз_", "(-и(-и(-и&й'(-и_за&й(-и_за)"
	   	};
					
		for (String temp : password) {
			boolean valid = this.validate(temp);
			System.out.println("Password is invalid : " + temp + " , " + valid);
			Assert.assertEquals(false, valid);
	    }
	}
}
