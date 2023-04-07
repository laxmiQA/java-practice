package com.vtiger.runner;

import com.vtiger.testscript.VerifyLoginPageTestScriptLayer;
import com.vtiger.utility.Utility;

public class Run {
	public static void main(String[] args) {
		System.out.println("rahul");
		 Utility objUtility= new Utility();
		objUtility.report("asdfghjkl");
		VerifyLoginPageTestScriptLayer o=	new VerifyLoginPageTestScriptLayer(objUtility);
		o.verifyloginpage("tc001");
		
		
		objUtility.flushed();
		
	}

}
