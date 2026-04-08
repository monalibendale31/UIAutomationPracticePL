package com.ui.pages;

import org.openqa.selenium.By;

import com.ui.constants.Browser;
import com.ui.constants.Env;
import com.ui.utility.BrowserUtility;
import com.ui.utility.PropertiesUtil;

public final class HomePage extends BrowserUtility{
	
	private static final By SIGN_IN_LINK_LOCATOR=By.xpath("//a[contains(text(),'Sign in')]");

	public HomePage(Browser browserName) {
		super(browserName); //Call parent class constructor from child class construtor
		goToWebsite(PropertiesUtil.readProperty(Env.QA, "URL"));
		
	}
	
	public LoginPage goToLoginPage() {
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginpage=new LoginPage(getDriver());
		return loginpage;
		
	}
}
