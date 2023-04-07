package com.vtiger.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BaseOR {

@FindBy(name = "query_string" )
protected WebElement searchTB;
	
@FindBy(xpath = "//a[text()='Sign Out']" )
protected WebElement signOutBT;

@FindBy(xpath="//a[text()='Gmail Bookmarklet']")
protected WebElement gmail;

@FindBy(xpath="//a[text()='vtiger News']")
protected WebElement vtiger;

@FindBy(xpath="//a[text()='Feedback']")
protected WebElement fd;

@FindBy(xpath="//a[text()='Help']")
protected WebElement help ;

@FindBy(xpath="//a[text()='About Us']")
protected WebElement abs;

@FindBy(xpath="//a[text()='Sign Out']")
protected WebElement signout;

@FindBy(xpath="//table[@class=\"lvt small\"]/parent::div/following-sibling::table//input[@value=\"Delete\"]")
protected WebElement del;

@FindBy(xpath="//input[@value=\"Search...\"]")
protected WebElement search;

@FindBy(xpath="//table[@class=\"lvt small\"]/parent::div/following-sibling::table//input[@value=\"Mass Edit\"")
protected WebElement editmass;


//public BaseOR(WebDriver driver) {
//	// TODO Auto-generated constructor stub
//	PageFactory.initElements(driver, this);
}




