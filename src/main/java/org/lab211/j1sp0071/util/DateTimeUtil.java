package org.lab211.j1sp0071.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeUtil {
	private static final String DATE_PATTERN = "dd-MM-yyyy";

	private DateTimeUtil() {}

	public static Date parseDateStrict(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		sdf.setLenient(false);
		return sdf.parse(dateStr);
	}

	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		return sdf.format(date);
	}
}


