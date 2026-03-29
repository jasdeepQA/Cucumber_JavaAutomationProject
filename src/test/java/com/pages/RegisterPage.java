package com.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.util.YamlReader;



public class RegisterPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By signUpHeader = By.xpath("//h2[text()='New User Signup!']");
	private By signUpName = By.xpath("//input[@data-qa='signup-name']");
	private By signUpEmail = By.xpath("//input[@data-qa='signup-email']");
	private By signUpButton = By.xpath("//button[@data-qa='signup-button']");
	private By maleGender = By.id("uniform-id_gender1");
	private By femaleGender = By.id("uniform-id_gender2");
	private By password = By.id("password");
	private By dayElement = By.id("days");
	private By monthElement = By.id("months");
	private By yearElement = By.id("years");
	private By option1 = By.id("uniform-newsletter");
	private By option2 = By.id("uniform-optin");
	private By firstName = By.id("first_name");
	private By lastName = By.id("last_name");
	private By companyName = By.id("company");
	private By address1 = By.id("address1");
	private By address2 = By.id("address2");
	private By countryName = By.id("country");
	private By stateName = By.id("state");
	private By cityName = By.id("city");
	private By zipcode = By.id("zipcode");
	private By mobile_number = By.id("mobile_number");
	private By createAccountButton = By.xpath("//button[@data-qa='create-account']");
	private By conitnueButton = By.xpath("//a[@data-qa='continue-button']");
	private By deleteAccountButton = By.xpath("//a[@href='/delete_account']");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public boolean verifySignUpHeader() {
		return driver.findElement(signUpHeader).isDisplayed();
	}

	public void enterSignUpName(String name) {
		driver.findElement(signUpName).sendKeys(name);
	}

	public void enterSignUpEmail(String email) {
		driver.findElement(signUpEmail).sendKeys(email);
	}

	public void clickOnSignUpButton() {
		driver.findElement(signUpButton).click();
	}

	public boolean verifyDisplayedText(String ExpectedText) {
		
//		return driver.getPageSource().contains(ExpectedText);
		
		try {
			System.out.println("//*[contains(text(),'"+ExpectedText+"')]");
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+ExpectedText+"')]"))).isDisplayed();
		}
		catch (Exception e) {
			return false;
		}
		
		
	}

	public void fillAccountDetails() throws Exception {
//		Map<String, String> data= datatable.asMaps().get(0);

		if (YamlReader.getValueByPath("register_user.title", String.class).equalsIgnoreCase("Mr.")) {
			driver.findElement(maleGender).click();
		} else {
			driver.findElement(femaleGender).click();
		}

		driver.findElement(password).sendKeys(YamlReader.getValueByPath("register_user.password", String.class));

//		String stringDate = data.get("Date of Birth");

//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//	    Date date = simpleDateFormat.parse(stringDate);
//	    
//	    String day = new SimpleDateFormat("d").format(date);
//	    String month = new SimpleDateFormat("M").format(date);
//	    String year = new SimpleDateFormat("yyyy").format(date);

		int day = YamlReader.getValueByPath("register_user.dob.day", Integer.class);
		int month = YamlReader.getValueByPath("register_user.dob.month", Integer.class);
		int year = YamlReader.getValueByPath("register_user.dob.year", Integer.class);
		Select dayDropDown = new Select(driver.findElement(dayElement));
		dayDropDown.selectByIndex(day);

		Select monthDropDown = new Select(driver.findElement(monthElement));
		monthDropDown.selectByIndex(month);

		Select yearDropDown = new Select(driver.findElement(yearElement));
		yearDropDown.selectByValue(String.valueOf(year));

		// TODO Auto-generated method stub

	}

	public void clickOnSelectbutton(String checkBoxOption) {
		WebElement element;
		if (checkBoxOption.equals("Sign up for our newsletter!")) {
			element = driver.findElement(option1);
		} else if (checkBoxOption.equals("Receive special offers from our partners!")) {
			element = driver.findElement(option2);
		} else {
			throw new IllegalArgumentException("Invalid checkbox option: " + checkBoxOption);
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

	}

	public void fillPersonalDetails() {
		// TODO Auto-generated method stub

//		Map<String, String> personalDetails = datatable.asMaps().get(0);
//		driver.findElement(firstName).sendKeys(personalDetails.get("First Name"));
//		driver.findElement(lastName).sendKeys(personalDetails.get("Last Name"));
//		driver.findElement(companyName).sendKeys(personalDetails.get("Company"));
//		driver.findElement(address1).sendKeys(personalDetails.get("Address"));
//		driver.findElement(address2).sendKeys(personalDetails.get("Address2"));
//		
//		Select selectCountry = new Select(driver.findElement(countryName));
//		selectCountry.selectByValue(personalDetails.get("Country"));
//		
//		driver.findElement(stateName).sendKeys(personalDetails.get("State"));
//		driver.findElement(cityName).sendKeys(personalDetails.get("City"));
//		driver.findElement(zipcode).sendKeys(personalDetails.get("Zipcode"));
//		driver.findElement(mobile_number).sendKeys(personalDetails.get("Mobile Number"));

		driver.findElement(firstName)
				.sendKeys(YamlReader.getValueByPath("register_user.address.first_name", String.class));
		driver.findElement(lastName)
				.sendKeys(YamlReader.getValueByPath("register_user.address.last_name", String.class));
		driver.findElement(companyName)
				.sendKeys(YamlReader.getValueByPath("register_user.address.company", String.class));
		driver.findElement(address1)
				.sendKeys(YamlReader.getValueByPath("register_user.address.address1", String.class));
		driver.findElement(address2)
				.sendKeys(YamlReader.getValueByPath("register_user.address.address2", String.class));

		Select selectCountry = new Select(driver.findElement(countryName));
		selectCountry.selectByValue(YamlReader.getValueByPath("register_user.address.country", String.class));

		driver.findElement(stateName).sendKeys(YamlReader.getValueByPath("register_user.address.state", String.class));
		driver.findElement(cityName).sendKeys(YamlReader.getValueByPath("register_user.address.city", String.class));
		driver.findElement(zipcode).sendKeys(YamlReader.getValueByPath("register_user.address.zipcode", String.class));
		driver.findElement(mobile_number)
				.sendKeys(YamlReader.getValueByPath("register_user.address.mobile_number", String.class));

	}

	public void clickOnCreateAccountButton() {
		// TODO Auto-generated method stub
		
		wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(createAccountButton));
		driver.findElement(createAccountButton).click();

	}

	public void clickConitnueButton() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(conitnueButton)).click();
	}

	public void clickonDeleteButton() {

		driver.findElement(deleteAccountButton).click();

	}

	public void enterExistingSignUpEmail() {

		driver.findElement(signUpEmail).sendKeys(YamlReader.getValueByPath("existing_email_data.email", String.class));

	}

	public void enterExistingSignUpName() {

		driver.findElement(signUpName).sendKeys(YamlReader.getValueByPath("existing_email_data.name", String.class));
	}

	public boolean verifyErrorMessage(String expectedErrorMessage) {
		return driver.getPageSource().contains(expectedErrorMessage);

	}

}
