package org.kiennguyenfpt.j1sp0071.constants;

/**
 * Centralized user-facing and validation messages.
 */
public final class Message {
	private Message() {}

	public static final String ERR_TASKTYPE_RANGE = "Task Type must be in range 1-4";
	public static final String ERR_DATE_FORMAT = "Date must be in format dd-MM-yyyy";
	public static final String ERR_HALF_HOUR = "Time must be on half-hour increments (.0 or .5)";
	public static final String ERR_TIME_WINDOW = "Time must be within 8.0 to 17.5";
	public static final String ERR_FROM_LT_TO = "Plan From must be less than Plan To";
	public static final String ERR_ID_NOT_EXIST = "ID does not exist";
	public static final String ERR_FIELD_NULL = "%s is null";
	public static final String ERR_FIELD_EMPTY = "%s is empty";
}


