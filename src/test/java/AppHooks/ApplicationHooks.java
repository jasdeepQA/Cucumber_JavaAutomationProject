package AppHooks;

import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;

	@Before(order = 0)
	public void getProperty() {
		System.out.println("reading properties");
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		if (prop == null) {
			throw new RuntimeException("Properties file could not be loaded. Check config.properties.");
		}
	}

	@Before(order = 1)
	public void launchBrowser() {
		System.out.println("Launching browser...");
		String browserName = prop.getProperty("browser");
		if (browserName == null || browserName.isEmpty()) {
			throw new RuntimeException("Browser name is not set in config.properties");
		}

		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName); // Ensure WebDriver is initialized
		DriverFactory.getDriver(); // This ensures the driver is set before tests start

		if (driver == null) {
			throw new RuntimeException("Failed to initialize WebDriver for browser: " + browserName);
		}
	}

	@After(order = 0)
	public void quitBrowser() {
		if (driver != null) {
			
			DriverFactory.quitDriver();
		}
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {

		if (scenario.isFailed()) {

			String screenshotName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");

			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

			scenario.attach(screenshot, "image/png", screenshotName);
		}
	}

}
