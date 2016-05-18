package org.fundacionjala.automation.framework.utils.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RMGenerator {
    public static String getIsoTime(int nextHoursFromCurrentTime) {
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.HOUR, nextHoursFromCurrentTime + 4);
	SimpleDateFormat time = new SimpleDateFormat(
		"yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
	return time.format(calendar.getTime());
    }
}
