package com.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.qa.factory.DriverFactory;
import com.qa.util.YamlReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;

	public LoginSteps() {
		this.driver = DriverFactory.getDriver();
		this.loginPage = new LoginPage(driver);
		this.homePage = new HomePage(driver);
	}

	@Given("I navigate to automationexercise.com")
	public void i_navigate_to_automation_exercise() {
		if (DriverFactory.getDriver() == null) {
			throw new RuntimeException("WebDriver not initialized in LoginSteps. Check ApplicationHooks.");

		}
		System.out.println("Redirecting to: https://automationexercise.com/");
		DriverFactory.getDriver().get("https://automationexercise.com");

	}

	@Then("I should see the home page successfully")
	public void i_should_see_home_page_successfully() {

		Assert.assertTrue(homePage.verifyHomePage());
	}

	@When("I click on the Signup Login button")
	public void i_click_on_signup_login_button() {

		homePage.clickOnSignUpLoginButton();
	}

	@Then("I should see Login to your account visible")
	public void i_should_see_login_to_your_account_visible() {

		Assert.assertTrue(loginPage.loginHeader());
	}

	@When("I enter a valid email address and password")
	public void i_enter_valid_email_and_password() {
		loginPage.enterUsername(YamlReader.getValueByPath("login_valid_user.email", String.class));
		loginPage.enterPassword(YamlReader.getValueByPath("login_valid_user.password", String.class));
	}

	@When("I enter a invalid email address and password")
	public void i_enter_invalid_email_and_password() {
		loginPage.enterUsername(YamlReader.getValueByPath("login_invalid_user.email", String.class));
		loginPage.enterPassword(YamlReader.getValueByPath("login_invalid_user.password", String.class));
	}

	@And("I click the login button")
	public void i_click_login_button() {
		loginPage.clickOnLoginButton();

	}

	@Then("I should see Logged in as username visible")
	public void i_should_see_logged_in_as_username_visible() {

		Assert.assertTrue(homePage.verifyLoginMessage());

	}

	@When("I click on logout button")
	public void i_click_on_button() {
		loginPage.clickOnLogoutButton();
	}

}
