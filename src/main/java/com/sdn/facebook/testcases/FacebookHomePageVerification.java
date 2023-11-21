package com.sdn.facebook.testcases;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.sdn.common.InitializeTestForFacebook;
import com.sdn.facebook.pageObjects.FacebookPageObjects;

public class FacebookHomePageVerification extends InitializeTestForFacebook {
	
	
	@Test(priority=0)
	public void titleVerify() throws IOException {
		extentTest = extentReports.createTest("Validate Title of the Login Page");
		String expectedTitle = "Facebook – log in or sign up";
		String actualTitle = driver.getTitle();
		System.out.println("expected= " + expectedTitle + " " + "actual= " + actualTitle);
		extentTest.log(Status.INFO, "Getting the title from Login page");
		if(actualTitle.equals(expectedTitle)) {
			extentTest.log(Status.PASS, "Login Page Title is displayed as expected");
		}
		else {
			extentTest.log(Status.FAIL, "Login Page Title is " + actualTitle + " which is not as expected");
			String screenshotPath = properties.getProperty("screenshotPath");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File source =  screenshot.getScreenshotAs(OutputType.FILE);
			File destination = new File(screenshotPath+"title.png");
			FileHandler.copy(source, destination);	
			extentTest.addScreenCaptureFromPath(destination.toString());
		}
	}
	
	@Test(priority=1)
	public void logoVerify() {
		extentTest = extentReports.createTest("Verify Facebook logo is present");
		PageFactory.initElements(driver,FacebookPageObjects.class);
		String logoVerify = FacebookPageObjects.facebookLogo.getAttribute("src");
		if(logoVerify.toCharArray().length != 0) {
			extentTest.log(Status.PASS, "Facebook Logo is Displayed");
		}
		else {
			extentTest.log(Status.FAIL, "Facebook Logo is not Displayed");
		}
	}
	
	@Test(priority=2)
	public void buttonVerify() {
		extentTest = extentReports.createTest("Verify Create New Account Button is Enabled");
		PageFactory.initElements(driver,FacebookPageObjects.class);
		boolean createButtonVerify = FacebookPageObjects.createNewAccountButton.isEnabled();
		if(createButtonVerify) {
			extentTest.log(Status.PASS, "Create New Account Button is Enabled");
			
		}
		else {
			extentTest.log(Status.FAIL, "Create New Account Button is disabled");
		}
	}
}
