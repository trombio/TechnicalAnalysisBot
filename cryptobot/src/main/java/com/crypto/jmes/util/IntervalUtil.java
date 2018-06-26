package com.crypto.jmes.util;

import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.joda.time.Hours;
import org.ta4j.core.Bar;

public class IntervalUtil {

	public static int getPeriodsFromBarUntilNow(Bar lastBar, Interval interval){
		int totalHours = 0;
		int totalDays = 0;
		int periods = 0;
		switch(interval){
		case FOUR_HOUR:
		case SIX_HOUR:
		case HOUR:	
			totalHours = getHoursUntilNow(lastBar);
			if(totalHours > 0) {
				periods = totalHours / interval.getValue();
			}
			return periods;
		case DAILY:
		case WEEKLY:
			totalDays = getDaysUntilNow(lastBar);
			if(totalDays > 0) {
				periods = totalDays / interval.getValue();
			}
			return periods;
		default:
			return 0;
		}
	}
	
	public static int getPeriodsFromBars(Bar firstBar, Bar lastBar, Interval interval){
		int totalHours = 0;
		int totalDays = 0;
		int periods = 0;
		switch(interval){
		case FOUR_HOUR:
		case SIX_HOUR:
		case HOUR:	
			totalHours = getHours(firstBar, lastBar);
			if(totalHours > 0) {
				periods = totalHours / interval.getValue();
			}
			return periods;
		case DAILY:
		case WEEKLY:
			totalDays = getDays(firstBar, lastBar);
			if(totalDays > 0) {
				periods = totalDays / interval.getValue();
			}
			return periods;
		default:
			return 0;
		}
	}

	private static int getHours(ZonedDateTime zdt, ZonedDateTime zdt2) {
		Hours h = Hours.hoursBetween(new org.joda.time.Instant(zdt.toInstant().toEpochMilli()),
				new org.joda.time.Instant(zdt2.toInstant().toEpochMilli()));
		long hours = h.getHours();
		int periods = (int)hours;
		return periods;
	}
	
	private static int getDays(ZonedDateTime zdt, ZonedDateTime zdt2) {
		Period p = Period.between(zdt.toLocalDate(), zdt2.toLocalDate());
		
		int periods = p.getDays();
		return periods;
	}
	
	private static int getHoursUntilNow(Bar b){
		ZonedDateTime zdt = b.getEndTime();
		ZoneId zone = zdt.getZone();
		ZonedDateTime zdt2 = ZonedDateTime.now(zone);

		return getHours(zdt, zdt2);
	}

	private static int getDaysUntilNow(Bar b){
		ZonedDateTime zdt = b.getEndTime();
		ZoneId zone = zdt.getZone();
		ZonedDateTime zdt2 = ZonedDateTime.now(zone);
		
		return getDays(zdt, zdt2);
	}
	
	private static int getHours(Bar b1, Bar b2){
		return getHours(b1.getEndTime(), b2.getEndTime());
	}

	private static int getDays(Bar b1, Bar b2){
		return getDays(b1.getEndTime(), b2.getEndTime());
	}

}