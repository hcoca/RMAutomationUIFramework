package org.fundacionjala.automation;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		format = { "pretty", "html:reports/cucumber" },
		features = "src/test/resources/features/"
)
public class RunnerTest {

}
