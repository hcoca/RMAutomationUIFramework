package org.fundacionjala.automation.scenario;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.TestNGCucumberRunner;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/resources/features/admin/resource/") 

public class ResourceRunnerTest {
	 @Test
	  public void run_cukes () throws IOException {
	    new TestNGCucumberRunner(getClass()).runCukes();
	  }
}
