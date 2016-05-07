package org.fundacionjala.automation.framework.maps.tablet.scheduler;

public class SchedulerMap {

	public static final String ORGANIZER_TEXT_FIELD = "//input[@id='txtOrganizer'][@placeholder='User name'][@type='text']";
	public static final String SUBJECT_TEXT_FIELD = "//input[@id='txtSubject'][@type='text'][@ng-model='editable.title']";
	public static final String ATTENDEES_TEXT_FIELD = "//input[@type='text'][@ng-model='buffer'][@placeholder='Press enter or semicolon to confirm']";
	public static final String CREATE_BUTTON = "//button[@class='clean item item-btn'][@ng-show='state.edit === editStates.CREATE']";
}
