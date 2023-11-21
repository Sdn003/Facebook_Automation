package com.sdn.facebook.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookPageObjects {
	
	@FindBy(xpath ="//title[@id= 'pageTitle']")
	public static WebElement loginPageTitle;
	
	@FindBy(xpath="//img[@class = 'fb_logo _8ilh img']")
	public static WebElement facebookLogo;
	
	@FindBy(xpath="//a[text() = 'Create new account']")
	public static WebElement createNewAccountButton;


}
