package com.sdn.gitbash;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class InitializeTestGitBash  {

	WebDriver driver  ;

	@Test
	public void openBrowser() {

		ChromeOptions chrome = new ChromeOptions();
		chrome.addArguments("--remote-allow-origin = *");
		chrome.addArguments("--start-maximized");
		driver  = new ChromeDriver(chrome);
		driver.get("https://facebook.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void verifyLoginPage() {
		boolean logoVerify = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div[1]/div/img")).isDisplayed();
		Assert.assertTrue(logoVerify);
	}



	@Test
	public void takeScreenshot() throws AWTException, IOException {
		Robot robot = new Robot();
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

		Rectangle rect = new Rectangle(screensize);
		BufferedImage source = robot.createScreenCapture(rect);

		File destination = new File("C:\\eclipse\\gitbash\\Screenshots\\screenshot.png");
		ImageIO.write(source, "png", destination);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
