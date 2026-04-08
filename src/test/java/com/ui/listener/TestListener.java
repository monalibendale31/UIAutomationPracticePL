package com.ui.listener;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase1;
import com.ui.utility.BrowserUtility;
import com.ui.utility.ExtentReporterUtility;
import com.ui.utility.LoggerUtility;

public class TestListener implements ITestListener {

	Logger logger = LoggerUtility.getLogger(this.getClass());
	ExtentSparkReporter extentSparkReporter; // Job to create HTML file--look,styling
	ExtentReports extentReports; // Job heavy lifting--data dump into html file using this extent reports
	ExtentTest extentTest; // job of extent test is to store info of extentTest which started,which test
							// executed

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + "  " + "Passed");

		ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + "  " + "Passed");
	}

	public void onTestFailure(ITestResult result) {

		logger.error(result.getMethod().getMethodName() + "     " + "FAILED");
		logger.error(result.getThrowable().getMessage());
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + "" + "FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());
		Object testclass = result.getInstance();

		BrowserUtility browserUtility = ((TestBase1) testclass).getInstance();
		logger.info("Capturing screenshots for failed tests");
		String screenShotPath = "";
		try {
			screenShotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Attaching the screenshot to the HTML file");
		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenShotPath);
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + "   " + "SKIPPED");
		ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + "   " + "SKIPPED");
	}

	public void onStart(ITestContext context) {
		logger.info("Test Suite Started");
		ExtentReporterUtility.setupSparkReporter("report.html");

	}

	public void onFinish(ITestContext context) {
		logger.info("Test Suite completed");
		ExtentReporterUtility.flushReport();
	}
}
