package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import com.ui.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.utility.BrowserUtility;
import com.ui.utility.LoggerUtility;

public class TestBase1 {

	protected HomePage homepage;
	Logger logger=LoggerUtility.getLogger(this.getClass());


	@BeforeMethod(description = "Load the HomePage of the website")
	public void setup() {
		logger.info("Load the homepage of the website");
		homepage = new HomePage(Browser.CHROME);
	}
	
	public BrowserUtility getInstance() {
		return homepage;
	}
}
