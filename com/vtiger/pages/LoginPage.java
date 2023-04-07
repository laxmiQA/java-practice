package com.vtiger.pages;

import java.util.ArrayList;

import com.vtiger.or.LoginPageOR;
import com.vtiger.utility.Utility;

public class LoginPage extends LoginPageOR {
	private Utility objutility;

	public LoginPage(Utility objUtility) {
		super(objUtility.getdriver());
		this.objutility = objUtility;
	}

	protected HomePage validLogin() {
		// TODO Auto-generated method stub
		objutility._hitURL();
		String username = objutility.readData("username");

		objutility.send(userNameTb, "UserNameBox", username);
		String userpassword = objutility.readData("userpassword");
		objutility.send(userPasswordTb, "passwordBox", userpassword);
		objutility.click(loginBt, "Login Button");
		return new HomePage(objutility);
	}

	protected void inValidLogin() {
		// TODO Auto-generated method stub
		objutility._hitURL();
		objutility.send(userNameTb, "UserNameBox", "xyz");
		objutility.send(userPasswordTb, "passwordBox", "1234");
		objutility.click(loginBt, "Login Button");

	}

	protected void getOptionsOfColorThemeDropdown() {
		ArrayList<String> options = objutility.getDropdownOptions(loginThemeDropDown, "colorTheme Dropdown");
		System.out.println("colorTheme DorpDown options are :-" + options);

	}

	protected void getOptionsOfLanguageDropdown() {
		ArrayList<String> options = objutility.getDropdownOptions(loginLanguage, "launguage Dropdown");
		System.out.println("language DorpDown options are :-" + options);

	}
}
