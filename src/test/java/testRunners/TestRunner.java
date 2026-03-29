package testRunners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty",
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
							"timeline:test-output-thread/",
							"rerun:target/failedrerun.txt" },
                            monochrome = true, 
                            tags = "@login",
//                            tags = "@login or @register",
                            publish = true,
                            glue = {"com.stepDefinitions", "AppHooks" }, 
                            features = "src/test/resources/features")

public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();}
	
}


