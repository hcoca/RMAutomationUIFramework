package org.fundacionjala.automation.scenario;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/resources/features/admin/resources-associations/resourcesAssociations.feature",
				 format = { "pretty", "html:reports/cucumber" , "json:./cucumber.json"}) 


public class ResourceRunnerTest extends AbstractTestNGCucumberTests{
	              
}
