package org.fundacionjala.automation.scenario.steps.admin.resource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class UpdateResourceSteps {
	
	@Given("^I have a resource created$")
	public void i_have_a_resource_created() throws Throwable {
		System.out.println("ROOM_NAME");
	}

	@When("^I modify the \"([^\"]*)\" field with value \"([^\"]*)\"$")
	public void i_modify_the_name_field_with_value(String name, String value) throws Throwable {
		System.out.println("ROOM_NAME" + name + value);
	}

	@When("^I save the modifications$")
	public void i_save_the_modifications() throws Throwable {
		System.out.println("ROOM_NAME");
	}
	@Then("^the resource is modified according the changes\\.$")
	public void the_resource_is_modified_according_the_changes() throws Throwable {
		System.out.println("ROOM_NAME");
	}
	

}
