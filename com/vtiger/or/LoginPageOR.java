package com.vtiger.or;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.base.BasePage;
import com.vtiger.utility.Utility;

public class LoginPageOR extends BasePage {

	private static Utility objutility;

	public LoginPageOR(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(objutility);
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "login_language")
	protected WebElement loginLanguage;

	@FindBy(name = "login_theme")
	protected WebElement loginThemeDropDown;

	@FindBy(name = "user_name")
	protected WebElement userNameTb;

	@FindBy(name = "user_password")
	protected WebElement userPasswordTb;

	@FindBy(name = "Login")
	protected WebElement loginBt;

}
