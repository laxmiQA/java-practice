package com.vtiger.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utility {// Singleton class for singlecopy of object.
	

	
	public WebDriver getdriver() {//to use driver call the method of Utility 's class getdriver();
		if (driver == null) {
			test.log(Status.FAIL, "Failled   browser(driver instance) is null");
		}
		return driver;

	}
	

	public void validatePageTitle(String expectedTitle) {
		// TODO Auto-generated method stub
		try {
			String CurrentTitle = driver.getTitle();
			if (expectedTitle.equals(CurrentTitle)) {
				test.log(Status.INFO, "title matched with expected title");
			} else
				test.log(Status.FAIL,
						"title  mis-matched  with expected title  [ Current Title is:-" + CurrentTitle + "]");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static final WebElement Actions = null;
	private   WebDriver driver;
	private ExtentReports repo;
	private ExtentTest test;

	public void report(String filename) {
		// TODO Auto-generated method stub
		try {
			File obj = new File("REPORT\\" + filename + ".html");
			ExtentSparkReporter spark = new ExtentSparkReporter(obj);
			repo = new ExtentReports();
			repo.attachReporter(spark);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void createTest(String testnameID) {
		// TODO Auto-generated method stub
		try {
			test = repo.createTest(testnameID);
		} catch (Exception e) {
			// TODO: handle exceptio
			System.out.println("test variable not initilized   some exception occoured ");
			e.printStackTrace();
		}
	}

	public void flushed() {
		// TODO Auto-generated method stub
		try {
			repo.flush();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("report not generated due to abnormal execution of flush method");
		}
	}

	/**
	 * to launch browser.
	 */
	public void browserLaunch() {
		String browsername = readData("browser");
		try {
			if (browsername.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browsername.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions o=new ChromeOptions();
				o.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(o);
			} else if (browsername.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browsername.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			} else {
				test.log(Status.INFO, "INVALID Browser name:-" + browsername);
			}
			test.log(Status.INFO, browsername + "Browser launched Sucessfully !!");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(Status.INFO, browsername + "Browser  isn't launched  !!");
		}
	}

	public void _hitURL() {
		String url = readData("url");
		try {
			driver.get(url);
	setImplicitWait(10);
			test.log(Status.INFO, url + "  launched  sucessfully!");
		} catch (Exception e) {
			// TODO: handle exception
			driver.navigate().to(url);
			test.log(Status.INFO, url + "  launched  sucessfully!");
		} catch (Throwable e) {
			// TODO: handle exceptions
			test.log(Status.INFO, url + " not launched !");
		}
	}

	public void sS(String filename) {
		// TODO Auto-generated method stub
		try {
			File from = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File to = new File("ScreenShot\\" + filename + ".jpg");
			try {
				Files.copy(from, to);
				test.log(Status.INFO, "ScreenShot captured as file name :- " + filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				test.log(Status.INFO, "ScreenShot can't coppy to desired folder");
			}
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.INFO, "ScreenShot  not captured ");

		}
	}

	// public static WebDriver driver1=null;

	public WebElement getElement(String locatorname,String locatorvalue) {
		WebElement we = null;
		try {
			if (locatorname.equalsIgnoreCase("xpath"))
				return driver.findElement(By.xpath(locatorvalue));
			else if (locatorname.equalsIgnoreCase("id"))
				return driver.findElement(By.id(locatorvalue));
			else if (locatorname.equalsIgnoreCase("name"))
				return driver.findElement(By.name(locatorvalue));
			else if (locatorname.equalsIgnoreCase("css"))
				return driver.findElement(By.cssSelector(locatorvalue));
			else if (locatorname.equalsIgnoreCase("classname"))
				return driver.findElement(By.className(locatorvalue));
			else if (locatorname.equalsIgnoreCase("linktext"))
				return driver.findElement(By.linkText(locatorvalue));
			else if (locatorname.equalsIgnoreCase("partialLinkText"))
				return driver.findElement(By.partialLinkText(locatorvalue));
			else if (locatorname.equalsIgnoreCase("tagname"))
				return driver.findElement(By.tagName(locatorvalue));
			else
				test.log(Status.INFO, "INVALID locator type :-" + locatorname);

		} catch (NoSuchElementException e) {
			// TODO: handle exception
			test.log(Status.FAIL, "There is non such element present on ui");
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, " WEbElement not created ");
		}
		return we;
	}

//	public List<WebElement> getElements(WebElement we) {
//		List<WebElement> we = null;
//		try {
//			if (locatorname.equalsIgnoreCase("xpath"))
//				return driver.findElements(By.xpath(locatorvalue));
//			else if (locatorname.equalsIgnoreCase("id"))
//				return driver.findElements(By.id(locatorvalue));
//			else if (locatorname.equalsIgnoreCase("name"))
//				return driver.findElements(By.name(locatorvalue));
//			else if (locatorname.equalsIgnoreCase("css"))
//				return driver.findElements(By.cssSelector(locatorvalue));
//			else if (locatorname.equalsIgnoreCase("classname"))
//				return driver.findElements(By.className(locatorvalue));
//			else if (locatorname.equalsIgnoreCase("linktext"))
//				return driver.findElements(By.linkText(locatorvalue));
//			else if (locatorname.equalsIgnoreCase("partialLinkText"))
//				return driver.findElements(By.partialLinkText(locatorvalue));
//			else if (locatorname.equalsIgnoreCase("tagname"))
//				return driver.findElements(By.tagName(locatorvalue));
//			else
//				test.log(Status.INFO, "INVALID locator type :-" + locatorname);
//
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//			test.log(Status.FAIL, "There is non such element present on ui");
//		} catch (Exception e) {
//			// TODO: handle exception
//			test.log(Status.FAIL, " WEbElement not created ");
//		}
//		return we;
//	}
//
	public void send(WebElement we, String elementName, String valueToSend) {
		// TODO Auto-generated method stub
		try {
			we.clear();
			we.sendKeys(valueToSend);
			test.log(Status.INFO, "entered " + valueToSend + "in " + elementName);
		} catch (ElementNotInteractableException e) {
			new Actions(driver).sendKeys(we).perform();
			test.log(Status.INFO, "entered " + valueToSend + "in " + elementName);
			// } catch (Exception e) {
			
			// JavascriptExecutor jsobj = ((JavascriptExecutor) driver);
			// jsobj.executeScript("arguments[0].value='" + valueToSend + "';", we);
			// test.log(Status.INFO, "entered " + valueToSend + "in " + elementName);
		} catch (Throwable e) {
			test.log(Status.INFO, "can't enter value in this textbox .");
		}

	}

		public String date_time(ExtentTest test) {
		String datetime = null;
		try {
			DateFormat df = new SimpleDateFormat("MM_dd_yyyy-HH_MM_SS");
			return datetime = df.format(new Date());
		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.FAIL, "abnormal termination of method ,thus exception handeled ");
		}
		return datetime;

	}

	public void setImplicitWait(int maxTimeout) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(maxTimeout));
		} catch (Exception e) {
			test.log(Status.INFO, "Exception occur during implicitely wait");
		}
	}

	public void sizeOfElement(WebElement we, String elementname, int expectedHeight,
			int expectedWidth) {
		try {
			Dimension dimensionObj = we.getSize();
			int actualHeight = dimensionObj.getHeight();
			if (actualHeight == expectedHeight) {
				test.log(Status.PASS, elementname + " height is same as expected");
			} else {
				test.log(Status.FAIL, elementname + " height mis-matched {ActualHeight =" + actualHeight
						+ "  ExpectedHeight =" + expectedHeight + "}\"");
			}
			int actualwidth = dimensionObj.getWidth();
			if (dimensionObj.getWidth() == expectedWidth) {
				test.log(Status.PASS, elementname + " width is same as expected");
			} else {
				test.log(Status.FAIL, elementname + " width mis-matched {ActualWidth =" + actualwidth
						+ "  ExpectedWidth =" + expectedWidth + "}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(Status.INFO, "Exception handled");
		}
	}

	public void drag_and_drop(WebElement weSource, WebElement weTarget, String elementname) {
		try {
			Actions objaction = new Actions(driver);
			objaction.dragAndDrop(weSource, weTarget).build().perform();
			test.log(Status.INFO, elementname + "is draged and dropped successfully");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public void validateClick(WebElement we,String elementname) {
		try {
			if (we.isDisplayed() == true) {
				test.log(Status.INFO, elementname + "text box is displayed");
				if (we.isEnabled() == true) {
					test.log(Status.INFO, elementname + "text box is enabled");
					we.click();
					test.log(Status.INFO, elementname + "clicked performed successfully");
				} else {
					test.log(Status.FAIL, elementname + "text box is not enabled");
				}
			} else {
				test.log(Status.FAIL, elementname + "text box is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sS("Vtiger/REPORT/crm.html");
	}

	public void click(WebElement we,String elementname) {

		try {
			we.click();
			test.log(Status.INFO, elementname + "clicked performed successfully");
		} catch (Exception e) {
			e.printStackTrace();
			sS("Vtiger/REPORT/crm.html");
		}
	}

	public void closeBrowser() {
		try {
			driver.close();
			test.log(Status.INFO, "browser closed successfully");
		} catch (Exception e) {
			test.log(Status.INFO, "Exception occur while closing the browser");
		}
	}

	public void closeAllBrowser() {
		try {
			driver.quit();
			test.log(Status.INFO, "All browsers opened during Automation proccess closed successfully");
		} catch (Exception e) {
			test.log(Status.INFO, "Exception occur while closing the browsers");
			// TODO: handle exception
		}
	}

	public ArrayList<String> getDropdownOptions(WebElement we, String elementName) {
		// TODO Auto-generated method stub
		ArrayList<String> Texts = new ArrayList<String>();
		try {
			Select objSelect = new Select(we);
			List<WebElement> dropDownOptions = objSelect.getOptions();
			for (WebElement webElement : dropDownOptions) {
				String text = webElement.getText();
				Texts.add(text);
			}
			test.log(Status.INFO, "DropDown Options Are:-" + Texts);
			return Texts;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Texts;
	}

	/**
	 * 
	 * @param we
	 * @param elementname
	 */
	public void mouseover(WebElement we, String elementname) {
		try {
			Actions objActions = new Actions(driver);
			objActions.moveToElement(we).build().perform();
			test.log(Status.INFO, "element moved " + elementname + "successfully");
		} catch (Exception e) {
			test.log(Status.FAIL, "elementis not moved " + elementname + "successfully");
			e.printStackTrace();
		}
	}

	/**
	 * this method will select given option of DropDown
	 * 
	 * @param locatorName  xpath Or Name Or Css OR ect.
	 * @param locatorValue
	 * @param elementName  dropDown Element name
	 * @param key          [value Or Index OR Text]
	 * @param value        value of valueAttribute of a option
	 */
	public void dropdownHandle(WebElement we, String elementName, String key, Object value) {
		// TODO Auto-generated method stub
		try {

			Select objSelect = new Select(we);
			if (key.equalsIgnoreCase("value")) {
				objSelect.selectByValue((String) value);
				test.log(Status.INFO, ((String) value) + " option selected under " + elementName + "DropDown");

			} else if (key.equalsIgnoreCase("index")) {
				objSelect.selectByIndex((Integer) value);
				test.log(Status.INFO, value + " option selected under " + elementName + "DropDown");

			} else if (key.equalsIgnoreCase((String) value)) {
				objSelect.selectByVisibleText((String) value);
				test.log(Status.INFO, ((String) value) + " option selected under " + elementName + "DropDown");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void windowHandleBy_TITLE(String titleOfWindow, String url) {
		try {
			Set<String> handlevalue = driver.getWindowHandles();
			System.out.println(handlevalue.size());
			System.out.println(handlevalue);
			for (String str : handlevalue) {
				String nameOfWindow = driver.switchTo().window(str).getTitle();
				if (nameOfWindow.contains(titleOfWindow))
					;
				else if (nameOfWindow.contains(url)) {
					test.log(Status.INFO, "title of current window is --" + titleOfWindow);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(Status.INFO, titleOfWindow + " is not handled");
		}
	}

	public String readData(String key) {
		String pathOfProprtiesFile = "./src/test/resources/data.properties";
		// TODO Auto-generated method stub
		FileInputStream obj = null;
		try {
			obj = new FileInputStream(pathOfProprtiesFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties objProperties = new Properties();
		try {
			objProperties.load(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = objProperties.getProperty(key);
		return value;
	}

	public void windowHandleBy_URL(String url) {
		try {
			Set<String> handlevalue = driver.getWindowHandles();
			System.out.println(handlevalue.size());
			System.out.println(handlevalue);
			for (String str : handlevalue) {
				String nameOfWindow = driver.switchTo().window(str).getTitle();
				if (nameOfWindow.contains(url))
					;
				else if (nameOfWindow.contains(url)) {
					test.log(Status.INFO, "url of current window is --" + url);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(Status.INFO, url + " is not handled");
		}

	}

	public String getPageTitle(String elementname) {
		String title = null;
		try {
			return title = driver.getTitle();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return title;
	}

	public String getInnerText(WebElement we,String elementname) {
		String obj = null;
		try {
			obj = we.getText();

		} catch (Exception e) {
			// TODO: handle exception
			test.log(Status.INFO, "InnerText has not find");
		}
		return obj;

	}

	
		
	
	}


	

