package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.utility.ExcelReaderUtility;
import com.ui.utility.LoggerUtility;

/*Test method!!!
1.	Test Script is small
2.	You cannot have conditional statements, loops,try catch in your test method
3.	Test Scripts should follow test steps
4.	Reduce the use of local variable
5.	At least one assertion.
*/
@Listeners(com.ui.listener.TestListener.class)
public class LoginTest extends TestBase1{

	Logger logger=LoggerUtility.getLogger(this.getClass());
	@DataProvider(name = "loginData")
	public Object[][] getData() throws Exception {
		return ExcelReaderUtility.getExcelData("LoginTestData");
	}

	@Test(description = "Verifies valid user is able to login into the application", groups = { "smoke",
			"regression" }, dataProvider = "loginData", retryAnalyzer = com.ui.listener.MyRetryListener.class)
	public void loginTest(String username, String password) {

		logger.info("Validating the username after login");
		String userName = homepage.goToLoginPage().doLoginWith(username, password).getUserName();

		Assert.assertEquals(userName, "Test Test");

	}

}
