package com.bradley.gwt.user.client.ui.grid.schedule;

import java.util.Date;

public class DateMath {

	public static final int SECOND_LENGTH = 1000;
	
	public static final int MINUTE_LENGTH = 60 * SECOND_LENGTH; // millis
	
	public static final int HOUR_LENGTH = 60 * MINUTE_LENGTH;
	
	public static final int DAY_LENGTH = 24 * HOUR_LENGTH;
	
	public static Date addMinutes(final Date original, int quantity) {
		return new Date(original.getTime() + quantity * MINUTE_LENGTH);
	}
	
	public static Date addHours(final Date original, int quantity) {
		return new Date(original.getTime() + quantity * HOUR_LENGTH);
	}
	
	public static int getDifferenceInMinutes(final Date start, final Date end) {
		return (int) (end.getTime() - start.getTime()) / MINUTE_LENGTH;
	}
	
	public static Date getMidnight(final Date date) {
		return new Date(date.getTime() / DAY_LENGTH * DAY_LENGTH);
	}
	
	/**
	 * Take the date from one Date and the time from another Date and
	 * merge the two. We keep the day of the first and the time of the
	 * second.
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date mergeDateTime(final Date date, final Date time) {
		Date day = new Date(date.getTime() / DAY_LENGTH * DAY_LENGTH);
		int hours = extractHour(time);
		int minutes = extractMinute(time);
		int seconds = extractSecond(time);
		return new Date(day.getTime() + hours * HOUR_LENGTH + minutes * MINUTE_LENGTH + seconds * SECOND_LENGTH);
	}
	
	public static int extractSecond(final Date time) {
		long secondMillis = time.getTime() % MINUTE_LENGTH;
		
		return (int) (secondMillis / SECOND_LENGTH);
	}
	
	public static int extractHour(final Date time) {
		long hoursMillis = time.getTime() % DAY_LENGTH;
		
		return (int) (hoursMillis / HOUR_LENGTH);
	}
	
	public static int extractMinute(final Date time) {
		long minuteMillis = time.getTime() % HOUR_LENGTH;
		return (int) (minuteMillis / MINUTE_LENGTH);
	}
}
