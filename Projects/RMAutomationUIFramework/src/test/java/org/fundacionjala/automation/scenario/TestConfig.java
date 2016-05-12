package org.fundacionjala.automation.scenario;

import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestConfig {

    @Before
    public void beforeScenario(Scenario scenario) {

	LogManager.info("[START] Scenario:" + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
	
	final byte[] screenshot;
	if (scenario.isFailed()) {
	    screenshot = ((TakesScreenshot) BrowserManager
		    .getDriver()).getScreenshotAs(OutputType.BYTES);
	    scenario.embed(screenshot, "image/png");
	}
	LogManager.info("[FINISHED]");
    }
}
