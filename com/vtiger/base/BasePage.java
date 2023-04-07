package com.vtiger.base;

import org.openqa.selenium.support.PageFactory;

import com.vtiger.utility.Utility;

public class BasePage extends BaseOR {


	protected Utility objUtility;

	public BasePage(Utility objUtility) {
		// TODO Auto-generated constructor stub
		this.objUtility = objUtility;
		PageFactory.initElements(objUtility.getdriver(), this);
	}

	protected void signOut() {
		// TODO Auto-generated method stub
		objUtility.click(signOutBT, "signout Button");
	}

	protected void delete() {
		// TODO Auto-generated method stub
		objUtility.click(del, "DeleteButton");
		;
	}

	protected void search() {
		// TODO Auto-generated method stub
		objUtility.click(search, "searchbutton");
	}

	protected void gmail() {
		// TODO Auto-generated method stub
		objUtility.click(gmail, "gmailbutton");
	}

	protected void editmass() {
		// TODO Auto-generated method stub
		objUtility.click(editmass, "editmassbutton");
	}

	protected void help() {
		// TODO Auto-generated method stub
		objUtility.click(help, "helpbutton");
	}

	protected void about() {
		// TODO Auto-generated method stub
		objUtility.click(abs, "absbutton");
	}

	protected void find() {
		// TODO Auto-generated method stub

	}

}
