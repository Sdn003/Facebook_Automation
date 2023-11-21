package com.sdn.common;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InitializeTestForFacebook {

	public Properties properties = null;
	public WebDriver driver = null;
	
	public ExtentReports extentReports;
	public ExtentHtmlReporter htmlReporter ;
	public ExtentTest extentTest;
	

	public Properties loadProperties() throws IOException {
		InputStream fileInputStream=getClass().getClassLoader().getResourceAsStream("configuration.properties");
		properties = new Properties();
		properties.load(fileInputStream);
		return properties;
	}


	@BeforeTest
	public void launchBrowser() throws IOException {
		loadProperties();
		String extentReportPath = properties.getProperty("extentReportPath");
		extentReports = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter("facebookTestResults.html");
		extentReports.attachReporter(htmlReporter);
//		WebDriverManager.chromiumdriver().setup();
		ChromeOptions options = new ChromeOptions();
		String browser = properties.getProperty("browser");
		String url = properties.getProperty("url");
		extentTest = extentReports.createTest("Validate Launch of the Application");
		if(browser.equalsIgnoreCase("edge")) {
			options.setBinary(properties.getProperty("edgeBrowserLocation"));
			options.addArguments("--start-maximized");
			System.out.println(url);
			 driver = new ChromeDriver(options);
			 System.out.println(url);
			 driver.navigate().to(url);
		}
		else if(browser.equalsIgnoreCase("brave")) {
			options.setBinary(properties.getProperty("braveBrowserLocation"));
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			driver.navigate().to(url);
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.get(url);
		}
		else if(browser.equalsIgnoreCase("chrome")){
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			driver.get(url);
			extentTest.log(Status.PASS, "Application Launched Successfully in ChromeBrowser");
		}
		else {
			extentTest.log(Status.FAIL, "Browser Specification is invalid");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@AfterTest
	public void tearDown() {
		extentTest = extentReports.createTest("Closing the Browser");
		extentTest.log(Status.PASS, "Browser is Closed Successfully");
		driver.quit();
		extentReports.flush();
	}

}
