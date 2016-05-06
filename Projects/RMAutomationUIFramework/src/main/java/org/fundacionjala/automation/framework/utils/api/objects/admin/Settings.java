package org.fundacionjala.automation.framework.utils.api.objects.admin;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;

public class Settings extends RequestObject {
	
	public String authentication;
	public int daysWarningExpirationDateAccount;
	public String tabletColorPalette;
	
	public Settings(String authentication, int daysWarningExpirationDateAccount, String tabletColorPalette) {
		this.authentication = authentication;
		this.daysWarningExpirationDateAccount = daysWarningExpirationDateAccount;
		this.tabletColorPalette = tabletColorPalette;
	}
	
	public Settings(JSONObject obj) {
		authentication = obj.get("authentication").toString();
		daysWarningExpirationDateAccount = Integer.parseInt(obj.get("daysWarningExpirationDateAccount").toString());
		tabletColorPalette = obj.get("tabletColorPalette").toString();
	}
	
	public JSONObject getJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("authentication", this.authentication);
		jsonObject.put("daysWarningExpirationDateAccount", this.daysWarningExpirationDateAccount);
		jsonObject.put("tabletColorPalette", this.tabletColorPalette);
		return jsonObject;
	}
	
	public Settings() {
	}
}