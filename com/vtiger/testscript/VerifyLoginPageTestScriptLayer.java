package com.vtiger.testscript;

import com.vtiger.pages.LoginPage;
import com.vtiger.utility.Utility;

public class VerifyLoginPageTestScriptLayer  {

	private Utility objUtility;

	public VerifyLoginPageTestScriptLayer(Utility objUtility) {
		// TODO Auto-generated constructor stub
		this.objUtility = objUtility;
	}

	public void verifyloginpage(String testId) {
		objUtility.createTest(testId);
		objUtility.browserLaunch();
		LoginPage objLoginPage = new LoginPage(objUtility);
		objLoginPage.validLogin();
		objLoginPage.signOut();
		objLoginPage.delete();
		
	}

}
