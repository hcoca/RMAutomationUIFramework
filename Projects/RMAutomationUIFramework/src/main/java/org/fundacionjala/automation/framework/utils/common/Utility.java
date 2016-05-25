package org.fundacionjala.automation.framework.utils.common;
/**
 * This class contains functions like handle of dates, or others in general
 * @author alejandraneolopan
 *
 */
public class Utility {
    /**
     * This method allows you update a time in String HH:MM format according
     * to the status "delayed" or "ahead" how much time it must be delayed
     * or ahead
     * @param timeStartHHMM e.g. "08:00"
     * @param timeEndHHMM e.g. "09:00"
     * @param status "delayed", "ahead"
     * @param hours Integer positive e.g. 1
     * @return String with a time updated e.g. 09:00
     */
    public static String getnewStartTimeByHour(String timeStartHHMM,
	    String timeEndHHMM, String status, int hoursToMove) {
	String hourIni, hourEnd;
	int hourStartInt, duration, hourEndInt;

	hourIni = timeStartHHMM.split(":")[0];
	hourEnd = timeEndHHMM.split(":")[0];
	hourStartInt = Integer.parseInt(hourIni);
	hourEndInt = Integer.parseInt(hourEnd);
	duration = hourEndInt - hourStartInt;

	if (status.contains("ahead")) {

	    if (hourStartInt - hoursToMove < 0) {
		return convertHourToString(0);
	    }
	    return convertHourToString(hourStartInt - hoursToMove);
	}

	if (hourStartInt + duration + hoursToMove > 24) {
	    return convertHourToString(24 - duration);
	}
	if (hourStartInt + duration + hoursToMove == 24) {
	    return convertHourToString(0);
	}
	return convertHourToString(hourStartInt + duration + hoursToMove);

    }
    
    /**
     * This method allows you update a time in String HH:MM format according
     * to the status "delayed" or "ahead" how much time it must be delayed
     * or ahead
     * @param timeEndHHMM e.g. "08:00"
     * @param status "delayed", "ahead"
     * @param hours Integer positive e.g. 1
     * @return String with a time updated e.g. 09:00
     */
    public static String getnewEndTimeByHour(String timeStartHHMM,
	    String timeEndHHMM, String status, int hoursToMove) {
	String hourIni, hourEnd;
	int hourStartInt, duration, hourEndInt;

	hourIni = timeStartHHMM.split(":")[0];
	hourEnd = timeEndHHMM.split(":")[0];
	hourStartInt = Integer.parseInt(hourIni);
	hourEndInt = Integer.parseInt(hourEnd);
	duration = hourEndInt - hourStartInt;

	if (status.contains("ahead")) {
	    if (hourEndInt - duration - hoursToMove <= 0) {
		return convertHourToString(duration);
	    }
	    return convertHourToString(hourEndInt - hoursToMove);
	}

	if (hourEndInt + hoursToMove >= 24) {
	    return convertHourToString(0);
	}

	return convertHourToString(hourEndInt + hoursToMove);

    }
    
    /**
     * This method allows convert and hour int (e.g. 1 --> 01:00:00.000)
     * @param hour
     * @return String time formated
     */
    private static String convertHourToString(int hour) {
	String browser = PropertiesReader.getBrowser();
	if (browser.equals("firefox") || browser.equals("safari")) {
	    return String.format("%02d:00:00.000", hour);
	}
	if (hour > 12) {
	    return String.format("%02d:00 PM", hour - 12);
	}
	return String.format("%02d:00 AM", hour);

    }
}
