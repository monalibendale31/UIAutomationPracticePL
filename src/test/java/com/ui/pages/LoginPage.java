package com.ui.pages;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ui.utility.BrowserUtility;
import com.ui.utility.LoggerUtility;

public final class LoginPage extends BrowserUtility{
	
	private static final By EMAIL_TEXTBOX_LOCATOR = By.id("email");
	private static final By PASSWORD_TEXTBOX_LOCATOR = By.id("passwd");
	private static final By SUBMIT_BUTTON_LOCATOR = By.id("SubmitLogin");
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public MyAccountPage doLoginWith(String emailAddress,String password) {
		logger.info("Entering username and password");
		enterText(EMAIL_TEXTBOX_LOCATOR,emailAddress);
		enterText(PASSWORD_TEXTBOX_LOCATOR,password);
		logger.info("Clicking on submit button");
		clickOn(SUBMIT_BUTTON_LOCATOR);
		MyAccountPage myaccount=new MyAccountPage(getDriver());
		return myaccount;
		
	}

	
}
