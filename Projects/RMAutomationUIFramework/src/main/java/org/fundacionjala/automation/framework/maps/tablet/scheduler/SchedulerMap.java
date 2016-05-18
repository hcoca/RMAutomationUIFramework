package org.fundacionjala.automation.framework.maps.tablet.scheduler;

public class SchedulerMap {

	public static final String ORGANIZER_TEXT_FIELD = "//input[@id='txtOrganizer'][@placeholder='User name'][@type='text']";
	public static final String SUBJECT_TEXT_FIELD = "//input[@id='txtSubject'][@type='text'][@ng-model='editable.title']";
	public static final String START_TEXT_FIELD = "//label[text()='From']/following::input[2]";
	public static final String END_TEXT_FIELD = "//label[text()='To']/following::input[2]";
	public static final String ATTENDEES_TEXT_FIELD = "//input[@type='text'][@ng-model='buffer'][@placeholder='Press enter or semicolon to confirm']";
	public static final String BODY_TEXT_FIELD = "//textarea[@id='txtBody']";
	public static final String CREATE_BUTTON = "//button[@class='clean item item-btn'][@ng-show='state.edit === editStates.CREATE']";
	public static final String UPDATE_BUTTON = "//span[text()='Update']/parent::button[@class='clean item item-btn'][@ng-show='state.edit === editStates.UPDATE']";
	public static final String REMOVE_BUTTON = "//span[text()='Remove']/parent::button[@class='clean item item-btn'][@ng-show='state.edit === editStates.UPDATE']";
	public static final String MEETING_BUTTON = "div/div/div[2]/div/div";
	public static final String TIME_LINE = "//div[@id='timelinePanel']/rm-vis/div/div[4]";
	public static final String ATTENDEES_LIST = "//ul[contains(@class,'list-inline')]";
	public static final String ATTENDEE_NAME = "li/span";
	public static final String ROOM_NAME_TITLE = "//span[text()='roomName']";
	public static final String CURRENT_TIME = "//span[@ng-bind='currentTime']";
	public static final String LEFT_TIME = "//div[@class='timeleft-remaining ng-binding']";
}
