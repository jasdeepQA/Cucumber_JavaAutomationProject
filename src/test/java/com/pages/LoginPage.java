package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private By emailId = By.xpath("//form[@action='/login']//input[@type='email']");
	private By passwordField = By.xpath("//form[@action='/login']//input[@type='password']");
	private By submit = By.xpath("//button[@data-qa='login-button']");
	private By logoutLinkLocator = By.linkText("Logout");
	private By loginHeader = By.xpath("//h2[contains(text(),'Login to your account')]");

	private By warningMessageLocater = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
//	private By loginButton = By.xpath("//button[contains(text(),'Login')]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void enterUsername(String userName) {

		System.out.println("UserName " + userName);
		driver.findElement(emailId).sendKeys(userName);

	}

	public void enterPassword(String password) {

		System.out.println("Password " + password);
		driver.findElement(passwordField).sendKeys(password);
	}

	public void clickOnLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
		
	}

	public boolean loginHeader() {
		return driver.findElement(loginHeader).isDisplayed();
	}

	public boolean verifyLoggedinLink() {
		return driver.findElement(logoutLinkLocator).isDisplayed();
	}

	public void clickOnLogoutButton() {
		driver.findElement(logoutLinkLocator).click();
	}

	public String verifyInvalidCredsMessage() {
		return driver.findElement(warningMessageLocater).getText();
	}

}
