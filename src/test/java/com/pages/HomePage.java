package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	private WebDriver driver;
	private By homePageElement = By.id("slider-carousel");
	private By signupLoginButton = By.xpath("//a[@href='/login']");
	private By loggedInMessage = By.xpath("//a[contains(text(),'Logged in as')]");

	public HomePage(WebDriver driver) {

		this.driver = driver;

	}

	public void clickOnSignUpLoginButton() {
		driver.findElement(signupLoginButton).click();
	}

	public boolean verifyHomePage() {
		return driver.findElement(homePageElement).isDisplayed();
	}

	public boolean verifyLoginMessage() {
		return driver.findElement(loggedInMessage).isDisplayed();
	}

}
