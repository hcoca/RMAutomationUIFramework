package org.fundacionjala.automation.scenario;


import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features ="src/test/resources/features/admin/resource") 

public class ResourceRunnerTest extends AbstractTestNGCucumberTests{
	              
}
