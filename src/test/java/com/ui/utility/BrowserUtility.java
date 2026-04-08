package com.ui.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.ui.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(ThreadLocal<WebDriver> driver) {
		BrowserUtility.driver = driver;
	}

	public BrowserUtility(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {

			driver.set(new EdgeDriver());
		} else {
			System.err.println("Invalid Browser name ");
		}
	}

	public BrowserUtility(Browser browserName) {
		if (browserName == Browser.CHROME) {
			logger.info("Launching browser for " + browserName);
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
		} else {
			logger.error("Invalid Browser name ....Please select chrome or edge");
			System.err.println("Invalid Browser name ");
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		if (browserName == Browser.CHROME && isHeadless) {
			if (isHeadless) {
				logger.info("Launching browser for " + browserName);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old"); // headless
				options.addArguments("window-size=1920,1080");

			} else {
				driver.set(new ChromeDriver());
			}

		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				logger.info("Launching browser for " + browserName);
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old"); // headless
				options.addArguments("disable-gpu");

			} else {
				driver.set(new EdgeDriver());
			}

		} else {
			logger.error("Invalid Browser name ....Please select chrome or edge");
			System.err.println("Invalid Browser name ");
		}
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}

	public void goToWebsite(String url) {
		logger.info("Visisting the website " + url);
		driver.get().get(url);

	}

	public void maximizeWindow() {
		logger.info("Maximize the browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding Element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now performing click ");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now eneter text " + textToEnter);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now returning the visibleText " + element);
		return element.getText();
	}

	public String takeScreenshot(String name) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();

		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM-mm-ss");
		String timeStamp = format.format(date);
		String path = System.getProperty("user.dir") + "//screenshots//" + name + "-" + "timeStamp" + ".png";

		File ScreenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, ScreenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}

}
