package org.fundacionjala.automation.scenario.steps.admin.service;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.RoomAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Room;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MeetingsAreDeletedWhenServiceDelete {
	private List<Service> listServices;
	private String idService = null;
	private Service currentService;
	private AdminPage home;
	
	@Given("^There is a mail service added$")
	public void there_is_a_mail_server_added() throws Throwable {
		LogManager.info("Scenario: All Meetings Are Deleted When Service is  Deleted");
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		for (Service service : listServices) {
				idService = service._id;
				currentService =service;
		}
		if (idService == null)
		{
			currentService = ServiceAPIManager.postRequest("http://172.20.208.84:4040/services?type=exchange",
					new Service(PropertiesReader.getExchangeHostname(),
							PropertiesReader.getExchangeConnectUserName(),
							PropertiesReader.getExchangeConnectPassword()));
			idService = currentService._id;
		}
		home = new AdminPage();
		if(BrowserManager.getDriver() != null)
		{
			home.refreshPage();
		}
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		login.setUserName(PropertiesReader.getUserName())
			.setPassword(PropertiesReader.getPassword())
			.clickOnSigInButton()
			.refreshPage();
	}
	
	@Given("^At least a meeting$")
	public void at_least_a_meeting() throws Throwable {
		home
		.leftMenu
		.clickOnIssuesButton()
		.clickOnConferenceRoomsButton()
		.doubleClickOnRoom("Room04")
		.SelectOutOfOrder()
		.activeOutOfOrder()
		.clickOnSave();
		
	}
	@When("^I delete the mail service$")
	public void i_delete_the_mail_service() throws Throwable {
		
		home
			.leftMenu
			.clickOnIssuesButton()
			.clickOnEmailServerButton()
			.clickOnRemoveButton()
			.clickOnYesButton()
			.waitItemDeleted();
	}
	@Then("^The meetings are deleted$")
	public void the_meetings_are_deleted() throws Throwable {
		List<Room> roomsList;
		List<Meeting> meetingsList;
		String roomId;
		int sumAllMeetings = 0;
		roomsList = RoomAPIManager.getRequest("http://172.20.208.84:4040/rooms");
		
		for (Room room : roomsList) {
			roomId = room._id;
			meetingsList = MeetingAPIManager.getRequest("http://172.20.208.84:4040/services",idService,roomId);
			sumAllMeetings = sumAllMeetings + meetingsList.size();
		}
		String errormsg = "Still there is at least " + sumAllMeetings + " meetings";
		if (sumAllMeetings == 0)
			LogManager.info("[PASSED]");
		else
			LogManager.error("[FAILED] - " + errormsg);
	
		Assert.assertEquals(sumAllMeetings, 0, errormsg);
		
		//Post-condition : Restore Service
		idService = null;
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		for (Service service : listServices) {
				idService = service._id;
		}
		if (idService == null)
		{
			ServiceAPIManager.postRequest("http://172.20.208.84:4040/services?type=exchange",
					new Service(PropertiesReader.getExchangeHostname(),
							PropertiesReader.getExchangeConnectUserName(),
							PropertiesReader.getExchangeConnectPassword()));
		}
	}
}
