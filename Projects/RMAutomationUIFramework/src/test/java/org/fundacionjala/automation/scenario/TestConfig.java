package org.fundacionjala.automation.scenario;

import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestConfig {
   
	@Before
	public void beforeScenario() {
		
		
	}

	@After
	public void afterScenario(Scenario scenario) {
		
		    if (scenario.isFailed()) {
		            final byte[] screenshot = ((TakesScreenshot) BrowserManager.getDriver())
		                        .getScreenshotAs(OutputType.BYTES);
		            scenario.embed(screenshot, "image/png"); 
		    }
	}
	
	
}
