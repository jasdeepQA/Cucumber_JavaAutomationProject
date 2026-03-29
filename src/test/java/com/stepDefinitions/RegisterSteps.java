package com.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.pages.RegisterPage;
import com.qa.factory.DriverFactory;
import com.qa.util.YamlReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterSteps {

	private WebDriver driver;
	private RegisterPage registerPage;

	public RegisterSteps()

	{
		this.driver = DriverFactory.getDriver();
		this.registerPage = new RegisterPage(driver);
	}

	@When("I enter a name and email address")
	public void i_enter_a_name_and_email_address() {

		registerPage.enterSignUpName(YamlReader.getValueByPath("register_user.name", String.class));
		registerPage.enterSignUpEmail(YamlReader.getValueByPath("register_user.email", String.class));

	}

	@And("I click on the Signup button")
	public void I_click_on_the_Signup_button() {
		registerPage.clickOnSignUpButton();
	}

	@When("I fill in account details:")
	public void i_fill_in_account_details() throws Exception {
		registerPage.fillAccountDetails();

	}

	@And("I select {string}")

	public void i_select(String checkBoxOption) {

		registerPage.clickOnSelectbutton(checkBoxOption);

	}

	@And("I fill in personal details")
	public void i_fill_in_personal_details() {

		registerPage.fillPersonalDetails();

	}

	@And("I click the Create Account button")
	public void i_click_the_button() {
		registerPage.clickOnCreateAccountButton();

	}

	@When("I click on the Continue button")
	public void I_click_on_the_continue_button() {
		registerPage.clickConitnueButton();
	}

	@Then("I should see {string} text")
	public void i_should_see_expected_text(String expectedText) {

		Assert.assertTrue(registerPage.verifyDisplayedText(expectedText));
	}

	@Given("I am logged in as {string}")
	public void I_am_logged_in_as(String expectedText) {
		registerPage.verifyDisplayedText(expectedText);
	}

	@When("I click the Delete Account button")
	public void I_click_the_Delete_Account_button() {
		registerPage.clickonDeleteButton();
	}

	@When("I enter a name and an already registered email address")
	public void i_enter_a_name_and_an_already_registered_email_address() {
		registerPage.enterExistingSignUpName();
		registerPage.enterExistingSignUpEmail();
	}

	@Then("I should see the error message {string}")
	public void i_should_see_the_error_message(String expectedErrorMessage) {
		
		Assert.assertTrue(registerPage.verifyErrorMessage(expectedErrorMessage));
	}

//	@Then("I should see \"Logged in as username\" text")
//	public void i_should_see_logged_in_as_username_text() {
//		// You may want to parameterize the username if needed
//		Assert.assertTrue(registerPage.verifyDisplayedText("Logged in as username"));
//	}

}