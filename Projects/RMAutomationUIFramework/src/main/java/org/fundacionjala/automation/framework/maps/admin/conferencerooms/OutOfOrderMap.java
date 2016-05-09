package org.fundacionjala.automation.framework.maps.admin.conferencerooms;

public class OutOfOrderMap {

	public static final String FROM_FIELD = "(//input[@type='text'])[4]";
	public static final String TO_FIELD = "(//input[@type='text'])[7]";
	public static final String FROM_FIELD_COMPLEX = "//input[@class='form-control ng-pristine ng-valid ng-isolate-scope ng-valid-date ng-valid-required ng-valid-parse ng-touched']";
	public static final String TO_FIELD_COMPLEX = "//input[@class='form-control ng-pristine ng-untouched ng-valid ng-isolate-scope ng-valid-date ng-valid-required ng-valid-parse']";
	public static final String ACTIVE_BUTTON = "//div[2]/div/div/div/label/span";
	public static final String BOX_BUTTON = "span.fa.fa-caret-down";
	public static final String CLOSED_FOR_MAINTENANCE_LINK = "Closed for maintenance";
	public static final String SAVE_BUTTON = "//button[@class='info']";
}
