package org.fundacionjala.automation.framework.utils.common;
/**
 * This class contains functions like handle of dates, or others in general
 * @author alejandraneolopan
 *
 */
public class Utility {
    /**
     * This method allows get the expected Start Time when the meeting
     * is dragged and dropped to the left or right (ahead or delayed)
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

	hourIni = timeStartHHMM.split(":")[0];//8
	hourEnd = timeEndHHMM.split(":")[0];//9
	hourStartInt = Integer.parseInt(hourIni);
	hourEndInt = Integer.parseInt(hourEnd);
	duration = hourEndInt - hourStartInt;//1

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
	return convertHourToString(hourStartInt + hoursToMove);

    }
    
    /**
     *This method allows get the expected End Time when the meeting
     * is dragged and dropped to the left or right (ahead or delayed) 
     * @param timeStartHHMM e.g. "08:00"
     * @param timeEndHHMM e.g. "09:00"
     * @param status "delayed", "ahead"
     * @param hoursToMove Integer positive e.g. 1
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
     * This method allows get the expected Start Time when the Start time
     * is pulled to the left or right (ahead or delayed) 
     * @param timeStartHHMM e.g. "08:00"
     * @param timeEndHHMM e.g. "09:00"
     * @param status "delayed", "ahead"
     * @param hoursToMove Integer positive e.g. 1
     * @return String with a time updated e.g. 09:00
     */
    public static String getnewStartTimeforStartPulledByHour(String timeStartHHMM,
	    String timeEndHHMM, String status, int hoursToMove) {
	String hourIni, hourEnd;
	int hourStartInt, hourEndInt;

	hourIni = timeStartHHMM.split(":")[0];
	hourEnd = timeEndHHMM.split(":")[0];
	hourStartInt = Integer.parseInt(hourIni);
	hourEndInt = Integer.parseInt(hourEnd);
	
	if (status.contains("ahead")) {

	    if (hourStartInt - hoursToMove < 0) {
		return convertHourToString(0);
	    }
	    return convertHourToString(hourStartInt - hoursToMove);
	}

	if (hourStartInt + hoursToMove >= hourEndInt) {
	    return convertHourToString(hourEndInt);
	}
	
	return convertHourToString(hourStartInt + hoursToMove);

    }
    
    /**
     * This method allows get the expected End Time when the End time
     * is pulled to the left or right (ahead or delayed) 
     * @param timeStartHHMM e.g. "08:00"
     * @param timeEndHHMM e.g. "09:00"
     * @param status "delayed", "ahead"
     * @param hoursToMove Integer positive e.g. 1
     * @return String with a time updated e.g. 09:00
     */
    public static String getnewEndTimeforEndPulledByHour(String timeStartHHMM,
	    String timeEndHHMM, String status, int hoursToMove) {
	String hourIni, hourEnd;
	int hourStartInt, hourEndInt;

	hourIni = timeStartHHMM.split(":")[0];//8
	hourEnd = timeEndHHMM.split(":")[0];//9
	hourStartInt = Integer.parseInt(hourIni);
	hourEndInt = Integer.parseInt(hourEnd);
	
	if (status.contains("ahead")) {
	    if (hourEndInt - hoursToMove <= hourStartInt) {
		return convertHourToString(hourStartInt);
	    }
	    return convertHourToString(hourEndInt - hoursToMove);
	}

	if (hourEndInt + hoursToMove >= 24) { 
	    return convertHourToString(0);
	}

	return convertHourToString(hourEndInt + hoursToMove);//9 + 1

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
	    return String.format("%02d:00", hour - 12);
	}
	return String.format("%02d:00", hour);

    }
}
