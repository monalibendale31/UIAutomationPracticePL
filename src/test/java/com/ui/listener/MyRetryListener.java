package com.ui.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.ui.constants.Env;
import com.ui.utility.PropertiesUtil;

public class MyRetryListener implements IRetryAnalyzer {
	
	private static final int MAX_NUMBER_OF_ATTEMPTS=Integer.parseInt(PropertiesUtil.readProperty(Env.QA, "MAX_NUMBER_OF_ATTEMPTS"));
	private static int currentAttempts;

	@Override
	public boolean retry(ITestResult result) {
		
		if(currentAttempts<MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempts++;
			return true;
		}
		
		return false;
	}

}
