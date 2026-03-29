package com.qa.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class DriverFactory {
	public static ThreadLocal<WebDriver> tdWebDriver = new ThreadLocal<>();

	public WebDriver init_driver(String browser) {

		System.out.println("Browser is: " + browser);

		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--disable-notifications");
			tdWebDriver.set(new ChromeDriver(options));

			System.out.println("Chrome browser is set");
		} else if (browser.equalsIgnoreCase("firefox")) {

			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--disable-notifications");
			tdWebDriver.set(new FirefoxDriver(options));

			System.out.println("Firefox browser is set");
		} else if (browser.equalsIgnoreCase("safari")) {

			SafariOptions options = new SafariOptions();
			options.setAutomaticInspection(true);
			options.setAutomaticProfiling(true);
			tdWebDriver.set(new SafariDriver(options));

			System.out.println("Safari browser is set");
		} else {
			throw new IllegalArgumentException("Invalid browser name: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		if (tdWebDriver == null) {
			throw new RuntimeException("WebDriver is not initialized. Call init_driver() first.");
		}
		return tdWebDriver.get();
	}

	public static void quitDriver() {
		if (getDriver() != null) {
			System.err.println("Quitting the driver...");
			
			getDriver().quit();
			tdWebDriver.remove();

		}
	}

}
