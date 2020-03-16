package com.sim.andon.web.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	private static final int[] dayArray = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static SimpleDateFormat sdf = new SimpleDateFormat();
	public static final long SECOND = 1000;
	public static final long MINUTE = 60 * SECOND;
	public static final long HOUR = 60 * MINUTE;
	public static final long DAY = 24 * HOUR;
	public static final long WEEK = 7 * DAY;

	private static final long ONE_MINUTE = 60000L;  
    private static final long ONE_HOUR = 3600000L;  
    private static final long ONE_DAY = 86400000L;  
    private static final long ONE_WEEK = 604800000L;  
  
    private static final String ONE_SECOND_AGO = "secs";  
    private static final String ONE_MINUTE_AGO = "mins";  
    private static final String ONE_HOUR_AGO = "hrs";  
    private static final String ONE_DAY_AGO = "days";  
    private static final String ONE_MONTH_AGO = "months";  
    private static final String ONE_YEAR_AGO = "years";  
    
	
	public static synchronized Calendar getCalendar() {
		return GregorianCalendar.getInstance();
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateMilliFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateMilliFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateMilliFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateMilliFormat(Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarMilliFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateMilliFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateSecondFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateSecondFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateSecondFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateSecondFormat(Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarSecondFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateSecondFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateMinuteFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateMinuteFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateMinuteFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateMinuteFormat(Date date) {
		String pattern = "yyyy-MM-dd HH:mm";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarMinuteFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateMinuteFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateDayFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateDayFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateDayFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateDayFormat(Date date) {
		String pattern = "yyyy-MM-dd";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarDayFormat(String strDate) {
		String pattern = "yyyy-MM-dd";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateDayFormat(String strDate) {
		String pattern = "yyyy-MM-dd";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateFileFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateFileFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateFileFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateFileFormat(Date date) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarFileFormat(String strDate) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateFileFormat(String strDate) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @return String
	 */
	public static synchronized String getDateW3CFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateW3CFormat(cal);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateW3CFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateW3CFormat(Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarW3CFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateW3CFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @param cal
	 * @return String
	 */
	public static synchronized String getDateFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateFormat(Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(date, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	/**
	 * @param strDate
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseDateFormat(strDate, pattern);
	}

	/**
	 * @param cal
	 * @param pattern
	 * @return String
	 */
	public static synchronized String getDateFormat(Calendar cal, String pattern) {
		return getDateFormat(cal.getTime(), pattern);
	}

	/**
	 * @param date
	 * @param pattern
	 * @return String
	 */
	public static synchronized String getDateFormat(Date date, String pattern) {
		synchronized (sdf) {
			String str = null;
			sdf.applyPattern(pattern);
			str = sdf.format(date);
			return str;
		}
	}

	/**
	 * @param strDate
	 * @param pattern
	 * @return java.util.Calendar
	 */
	public static synchronized Calendar parseCalendarFormat(String strDate, String pattern) {
		synchronized (sdf) {
			Calendar cal = null;
			sdf.applyPattern(pattern);
			try {
				sdf.parse(strDate);
				cal = sdf.getCalendar();
			} catch (Exception e) {
			}
			return cal;
		}
	}

	/**
	 * @param strDate
	 * @param pattern
	 * @return java.util.Date
	 */
	public static synchronized Date parseDateFormat(String strDate, String pattern) {
		synchronized (sdf) {
			Date date = null;
			sdf.applyPattern(pattern);
			try {
				date = sdf.parse(strDate);
			} catch (Exception e) {
			}
			return date;
		}
	}

	public static synchronized int getLastDayOfMonth(int month) {
		if (month < 1 || month > 12) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear()) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	public static synchronized int getLastDayOfMonth(int year, int month) {
		if (month < 1 || month > 12) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear(year)) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	public static synchronized boolean isLeapYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return isLeapYear(year);
	}

	public static synchronized boolean isLeapYear(int year) {
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 判断指定日期的年份是否是闰年
	 *
	 * @param date
	 *            指定日期。
	 * @return 是否闰年
	 */
	public static synchronized boolean isLeapYear(Date date) {
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		// int year = date.getYear();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		int year = gc.get(Calendar.YEAR);
		return isLeapYear(year);
	}

	public static synchronized boolean isLeapYear(Calendar gc) {
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		int year = gc.get(Calendar.YEAR);
		return isLeapYear(year);
	}

	/**
	 * 得到指定日期的前一个工作日
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的前一个工作日
	 */
	public static synchronized Date getPreviousWeekDay(Date date) {
		{
			/**
			 * 详细设计： 1.如果date是星期日，则减3天 2.如果date是星期六，则减2天 3.否则减1天
			 */
			GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
			gc.setTime(date);
			return getPreviousWeekDay(gc);
		}
	}

	public static synchronized Date getPreviousWeekDay(Calendar gc) {
		{
			/**
			 * 详细设计： 1.如果date是星期日，则减3天 2.如果date是星期六，则减2天 3.否则减1天
			 */
			switch (gc.get(Calendar.DAY_OF_WEEK)) {
			case (Calendar.MONDAY):
				gc.add(Calendar.DATE, -3);
				break;
			case (Calendar.SUNDAY):
				gc.add(Calendar.DATE, -2);
				break;
			default:
				gc.add(Calendar.DATE, -1);
				break;
			}
			return gc.getTime();
		}
	}

	/**
	 * 得到指定日期的后一个工作日
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的后一个工作日
	 */
	public static synchronized Date getNextWeekDay(Date date) {
		/**
		 * 详细设计： 1.如果date是星期五，则加3天 2.如果date是星期六，则加2天 3.否则加1天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 2);
			break;
		default:
			gc.add(Calendar.DATE, 1);
			break;
		}
		return gc.getTime();
	}

	public static synchronized Calendar getNextWeekDay(Calendar gc) {
		/**
		 * 详细设计： 1.如果date是星期五，则加3天 2.如果date是星期六，则加2天 3.否则加1天
		 */
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 2);
			break;
		default:
			gc.add(Calendar.DATE, 1);
			break;
		}
		return gc;
	}

	/**
	 * 取得指定日期的下一个月的最后一天
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个月的最后一天
	 */
	public static synchronized Date getLastDayOfNextMonth(Date date) {
		/**
		 * 详细设计： 1.调用getNextMonth设置当前时间 2.以1为基础，调用getLastDayOfMonth
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextMonth(gc.getTime()));
		gc.setTime(DateUtil.getLastDayOfMonth(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * 取得指定日期的下一个星期的最后一天
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个星期的最后一天
	 */
	public static synchronized Date getLastDayOfNextWeek(Date date) {
		/**
		 * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getLastDayOfWeek
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextWeek(gc.getTime()));
		gc.setTime(DateUtil.getLastDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * 取得指定日期的下一个月的第一天
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个月的第一天
	 */
	public static synchronized Date getFirstDayOfNextMonth(Date date) {
		/**
		 * 详细设计： 1.调用getNextMonth设置当前时间 2.以1为基础，调用getFirstDayOfMonth
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextMonth(gc.getTime()));
		gc.setTime(DateUtil.getFirstDayOfMonth(gc.getTime()));
		return gc.getTime();
	}

	public static synchronized Calendar getFirstDayOfNextMonth(Calendar gc) {
		/**
		 * 详细设计： 1.调用getNextMonth设置当前时间 2.以1为基础，调用getFirstDayOfMonth
		 */
		gc.setTime(DateUtil.getNextMonth(gc.getTime()));
		gc.setTime(DateUtil.getFirstDayOfMonth(gc.getTime()));
		return gc;
	}

	/**
	 * 取得指定日期的下一个星期的第一天
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个星期的第一天
	 */
	public static synchronized Date getFirstDayOfNextWeek(Date date) {
		/**
		 * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextWeek(gc.getTime()));
		gc.setTime(DateUtil.getFirstDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	public static synchronized Calendar getFirstDayOfNextWeek(Calendar gc) {
		/**
		 * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
		 */
		gc.setTime(DateUtil.getNextWeek(gc.getTime()));
		gc.setTime(DateUtil.getFirstDayOfWeek(gc.getTime()));
		return gc;
	}

	/**
	 * 取得指定日期的下一个月
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个月
	 */
	public static synchronized Date getNextMonth(Date date) {
		/**
		 * 详细设计： 1.指定日期的月份加1
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.MONTH, 1);
		return gc.getTime();
	}

	public static synchronized Calendar getNextMonth(Calendar gc) {
		/**
		 * 详细设计： 1.指定日期的月份加1
		 */
		gc.add(Calendar.MONTH, 1);
		return gc;
	}

	/**
	 * 取得指定日期的下一天
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一天
	 */
	public static synchronized Date getNextDay(Date date) {
		/**
		 * 详细设计： 1.指定日期加1天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return gc.getTime();
	}

	public static synchronized Calendar getNextDay(Calendar gc) {
		/**
		 * 详细设计： 1.指定日期加1天
		 */
		gc.add(Calendar.DATE, 1);
		return gc;
	}

	/**
	 * 取得指定日期的下一个星期
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的下一个星期
	 */
	public static synchronized Date getNextWeek(Date date) {
		/**
		 * 详细设计： 1.指定日期加7天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 7);
		return gc.getTime();
	}

	public static synchronized Calendar getNextWeek(Calendar gc) {
		/**
		 * 详细设计： 1.指定日期加7天
		 */
		gc.add(Calendar.DATE, 7);
		return gc;
	}

	/**
	 * 取得指定日期的所处星期的最后一天
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处星期的最后一天
	 */
	public static synchronized Date getLastDayOfWeek(Date date) {
		/**
		 * 详细设计： 1.如果date是星期日，则加6天 2.如果date是星期一，则加5天 3.如果date是星期二，则加4天
		 * 4.如果date是星期三，则加3天 5.如果date是星期四，则加2天 6.如果date是星期五，则加1天
		 * 7.如果date是星期六，则加0天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 6);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, 5);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, 4);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, 2);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 1);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 0);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 取得指定日期的所处星期的第一天
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处星期的第一天
	 */
	public static synchronized Date getFirstDayOfWeek(Date date) {
		/**
		 * 详细设计： 1.如果date是星期日，则减0天 2.如果date是星期一，则减1天 3.如果date是星期二，则减2天
		 * 4.如果date是星期三，则减3天 5.如果date是星期四，则减4天 6.如果date是星期五，则减5天
		 * 7.如果date是星期六，则减6天
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 0);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -1);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, -4);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, -5);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, -6);
			break;
		}
		return gc.getTime();
	}

	public static synchronized Calendar getFirstDayOfWeek(Calendar gc) {
		/**
		 * 详细设计： 1.如果date是星期日，则减0天 2.如果date是星期一，则减1天 3.如果date是星期二，则减2天
		 * 4.如果date是星期三，则减3天 5.如果date是星期四，则减4天 6.如果date是星期五，则减5天
		 * 7.如果date是星期六，则减6天
		 */
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 0);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -1);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.THURSDAY):
			gc.add(Calendar.DATE, -4);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, -5);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, -6);
			break;
		}
		return gc;
	}

	/**
	 * 取得指定日期的所处月份的最后一天
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处月份的最后一天
	 */
	public static synchronized Date getLastDayOfMonth(Date date) {
		/**
		 * 详细设计： 1.如果date在1月，则为31日 2.如果date在2月，则为28日 3.如果date在3月，则为31日
		 * 4.如果date在4月，则为30日 5.如果date在5月，则为31日 6.如果date在6月，则为30日
		 * 7.如果date在7月，则为31日 8.如果date在8月，则为31日 9.如果date在9月，则为30日
		 * 10.如果date在10月，则为31日 11.如果date在11月，则为30日 12.如果date在12月，则为31日
		 * 1.如果date在闰年的2月，则为29日
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.MONTH)) {
		case 0:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 1:
			gc.set(Calendar.DAY_OF_MONTH, 28);
			break;
		case 2:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 3:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 4:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 5:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 6:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 7:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 8:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 9:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 10:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 11:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		// 检查闰年
		if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY) && (isLeapYear(gc.get(Calendar.YEAR)))) {
			gc.set(Calendar.DAY_OF_MONTH, 29);
		}
		return gc.getTime();
	}

	public static synchronized Calendar getLastDayOfMonth(Calendar gc) {
		/**
		 * 详细设计： 1.如果date在1月，则为31日 2.如果date在2月，则为28日 3.如果date在3月，则为31日
		 * 4.如果date在4月，则为30日 5.如果date在5月，则为31日 6.如果date在6月，则为30日
		 * 7.如果date在7月，则为31日 8.如果date在8月，则为31日 9.如果date在9月，则为30日
		 * 10.如果date在10月，则为31日 11.如果date在11月，则为30日 12.如果date在12月，则为31日
		 * 1.如果date在闰年的2月，则为29日
		 */
		switch (gc.get(Calendar.MONTH)) {
		case 0:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 1:
			gc.set(Calendar.DAY_OF_MONTH, 28);
			break;
		case 2:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 3:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 4:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 5:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 6:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 7:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 8:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 9:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 10:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 11:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		// 检查闰年
		if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY) && (isLeapYear(gc.get(Calendar.YEAR)))) {
			gc.set(Calendar.DAY_OF_MONTH, 29);
		}
		return gc;
	}

	/**
	 * 取得指定日期的所处月份的第一天
	 *
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处月份的第一天
	 */
	public static synchronized Date getFirstDayOfMonth(Date date) {
		/**
		 * 详细设计： 1.设置为1号
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.set(Calendar.DAY_OF_MONTH, 1);
		return gc.getTime();
	}

	public static synchronized Calendar getFirstDayOfMonth(Calendar gc) {
		/**
		 * 详细设计： 1.设置为1号
		 */
		gc.set(Calendar.DAY_OF_MONTH, 1);
		return gc;
	}

	/**
	 * 将日期对象转换成为指定ORA日期、时间格式的字符串形式。如果日期对象为空，返回 一个空字符串对象，而不是一个空对象。
	 *
	 * @param theDate
	 *            将要转换为字符串的日期对象。
	 * @param hasTime
	 *            如果返回的字符串带时间则为true
	 * @return 转换的结果
	 */
	public static synchronized String toOraString(Date theDate, boolean hasTime) {
		/**
		 * 详细设计： 1.如果有时间，则设置格式为getOraDateTimeFormat()的返回值
		 * 2.否则设置格式为getOraDateFormat()的返回值 3.调用toString(Date theDate, DateFormat
		 * theDateFormat)
		 */
		DateFormat theFormat;
		if (hasTime) {
			theFormat = getOraDateTimeFormat();
		} else {
			theFormat = getOraDateFormat();
		}
		return toString(theDate, theFormat);
	}

	/**
	 * 将日期对象转换成为指定日期、时间格式的字符串形式。如果日期对象为空，返回 一个空字符串对象，而不是一个空对象。
	 *
	 * @param theDate
	 *            将要转换为字符串的日期对象。
	 * @param hasTime
	 *            如果返回的字符串带时间则为true
	 * @return 转换的结果
	 */
	public static synchronized String toString(Date theDate, boolean hasTime) {
		/**
		 * 详细设计： 1.如果有时间，则设置格式为getDateTimeFormat的返回值 2.否则设置格式为getDateFormat的返回值
		 * 3.调用toString(Date theDate, DateFormat theDateFormat)
		 */
		DateFormat theFormat;
		if (hasTime) {
			theFormat = getDateTimeFormat();
		} else {
			theFormat = getDateFormat();
		}
		return toString(theDate, theFormat);
	}

	/**
	 * 标准日期格式
	 */
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	/**
	 * 标准时间格式
	 */
	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	/**
	 * 带时分秒的标准时间格式
	 */

	/**
	 * ORA标准日期格式
	 */
	private static final SimpleDateFormat ORA_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	/**
	 * ORA标准时间格式
	 */
	private static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");


	/**
	 * 创建一个标准日期格式的克隆
	 *
	 * @return 标准日期格式的克隆
	 */
	public static synchronized DateFormat getDateFormat() {
		/**
		 * 详细设计： 1.返回DATE_FORMAT
		 */
		SimpleDateFormat theDateFormat = (SimpleDateFormat) DATE_FORMAT.clone();
		theDateFormat.setLenient(false);
		return theDateFormat;
	}

	/**
	 * 创建一个标准时间格式的克隆
	 *
	 * @return 标准时间格式的克隆
	 */
	public static synchronized DateFormat getDateTimeFormat() {
		/**
		 * 详细设计： 1.返回DATE_TIME_FORMAT
		 */
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) DATE_TIME_FORMAT.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	/**
	 * 创建一个标准ORA日期格式的克隆
	 *
	 * @return 标准ORA日期格式的克隆
	 */
	public static synchronized DateFormat getOraDateFormat() {
		/**
		 * 详细设计： 1.返回ORA_DATE_FORMAT
		 */
		SimpleDateFormat theDateFormat = (SimpleDateFormat) ORA_DATE_FORMAT.clone();
		theDateFormat.setLenient(false);
		return theDateFormat;
	}

	/**
	 * 创建一个标准ORA时间格式的克隆
	 *
	 * @return 标准ORA时间格式的克隆
	 */
	public static synchronized DateFormat getOraDateTimeFormat() {
		/**
		 * 详细设计： 1.返回ORA_DATE_TIME_FORMAT
		 */
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	/**
	 * 将一个日期对象转换成为指定日期、时间格式的字符串。 如果日期对象为空，返回一个空字符串，而不是一个空对象。
	 *
	 * @param theDate
	 *            要转换的日期对象
	 * @param theDateFormat
	 *            返回的日期字符串的格式
	 * @return 转换结果
	 */
	public static synchronized String toString(Date theDate, DateFormat theDateFormat) {
		/**
		 * 详细设计： 1.theDate为空，则返回"" 2.否则使用theDateFormat格式化
		 */
		if (theDate == null)
			return "";
		return theDateFormat.format(theDate);
	}

	// 由java.util.Date到java.sql.Date的类型转换
	public static synchronized java.sql.Date getSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	// 返回一个日期字符串在星期中的顺序
	public static synchronized int getDateInWeek(String strDate) {
		DateFormat df = DateFormat.getDateInstance();
		try {
			df.parse(strDate);
			Calendar c = df.getCalendar();
			int day = c.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
			return day;
		} catch (ParseException e) {
			return -1;
		}
	}

	// 一个日期上加上月
	public static synchronized String MonthAdd(Date startDate, int mm) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);

		calendar.add(Calendar.MONTH, mm);

		SimpleDateFormat d = new SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd HH:mm:ss");
		String returnDate = d.format(calendar.getTime());

		return returnDate;

	}

	// 一个日期上加天数
	public static synchronized String DateAdd(String startDate, int dd) {
		DateFormat df = DateFormat.getDateInstance();
		Date date = new Date();
		try {
			date = df.parse(startDate);
		} catch (Exception ex) {
			System.out.print(ex);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.DATE, dd);

		SimpleDateFormat d = new SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd");
		String returnDate = d.format(calendar.getTime());

		return returnDate;

	}

	// 一个日期上加dd个工作日
	public static synchronized String DateAddWorkday(String startDate, int dd) {
		DateFormat df = DateFormat.getDateInstance();
		Date date = new Date();
		try {
			date = df.parse(startDate);
		} catch (Exception ex) {
			System.out.print(ex);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		while (dd > 0) {
			// Calendar.DAY_OF_WEEK返回的 1 是星期天
			if ((calendar.get(Calendar.DAY_OF_WEEK) == 1) || (calendar.get(Calendar.DAY_OF_WEEK) == 7)) {
				calendar.add(Calendar.DATE, 1);
			} else {
				calendar.add(Calendar.DATE, 1);
				dd = -1;
			}
		}

		SimpleDateFormat d = new SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd");
		String returnDate = d.format(calendar.getTime());

		return returnDate;

	}

	// 返回当前日期,类型为格式"yyyy-mm-dd"的字符串
	public static synchronized String getDate() {
		SimpleDateFormat d = new SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd");
		Date nowdate = new Date();
		String str_date = d.format(nowdate);
		return str_date;
	}

	public static synchronized String DateToStr(Date date) {
		SimpleDateFormat d = new SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd");
		String str_date = d.format(date);
		return str_date;
	}

	// 返回指定年月的天数
	public static synchronized int getMonthDayNum(int year, int month) {
		if (month == 2) {
			return year % 400 != 0 && (year % 4 != 0 || year % 100 == 0) ? 28 : 29;
		}
		String SmallMonth = ",4,6,9,11,";
		return SmallMonth.indexOf(String.valueOf(String.valueOf((new StringBuffer(",")).append(String.valueOf(month)).append(",")))) < 0 ? 31 : 30;
	}

	// 返回当前时间
	public static synchronized String getTime() {
		Date date = new Date();
		Time time;
		time = new Time(date.getTime());
		String strTime = time.toString();
		return strTime;
	}

	public static synchronized int getYearMonthDate(String strDate, String style) {
		int year;
		int month;
		int day;
		int firstDash;
		int secondDash;
		if (strDate == null) {
			return 0;
		}
		firstDash = strDate.indexOf('-');
		secondDash = strDate.indexOf('-', firstDash + 1);
		if ((firstDash > 0) & (secondDash > 0) & (secondDash < strDate.length() - 1)) {
			year = Integer.parseInt(strDate.substring(0, firstDash));
			month = Integer.parseInt(strDate.substring(firstDash + 1, secondDash));
			day = Integer.parseInt(strDate.substring(secondDash + 1));
		} else {
			return 0;
		}
		if (style.equalsIgnoreCase("Y")) {
			return year;
		} else if (style.equalsIgnoreCase("M")) {
			return month;
		} else if (style.equalsIgnoreCase("D")) {
			return day;
		} else {
			return 0;
		}
	}

	// 返回两日期差的天数
	public static synchronized int DateDiff(Date date1, Date date2) {
		int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000);
		return i;
	}

	public static synchronized java.sql.Timestamp getTimestamp() {
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String mystrdate = myFormat.format(calendar.getTime());
			return java.sql.Timestamp.valueOf(mystrdate);
		} catch (Exception e) {
			return null;
		}
	}

	public static synchronized java.sql.Timestamp getTimestamp(String datestr) {
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String mystrdate = myFormat.format(myFormat.parse(datestr));
			return java.sql.Timestamp.valueOf(mystrdate);
		} catch (Exception e) {
			return null;
		}
	}

	public static synchronized java.sql.Timestamp getDate(String datestr) {
		datestr = datestr + " 00:00:00";
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = myFormat.parse(datestr);
			// System.out.println(date);
			// myFormat.applyLocalizedPattern("yyyy-MM-dd HH:mm:ss");
			String mystrdate = myFormat.format(date);
			return java.sql.Timestamp.valueOf(mystrdate);
		} catch (Exception e) {
			return null;
		}
	}

	public static synchronized Date StrToDate(String strDate) {
		int year;
		int month;
		int day;
		int firstDash;
		int secondDash;
		if (strDate == null) {
			return null;
		}
		firstDash = strDate.indexOf('-');
		secondDash = strDate.indexOf('-', firstDash + 1);
		if ((firstDash > 0) & (secondDash > 0) & (secondDash < strDate.length() - 1)) {
			year = Integer.parseInt(strDate.substring(0, firstDash));
			month = Integer.parseInt(strDate.substring(firstDash + 1, secondDash));
			day = Integer.parseInt(strDate.substring(secondDash + 1));
			Calendar c = Calendar.getInstance();
			c.set(year, month, day);
			return c.getTime();
		}
		return null;
	}



	// 返回当前日期,类型为格式"yyyy-mm-dd"的字符串
	public static synchronized String getDateByFormat(String format) {
		SimpleDateFormat d = new SimpleDateFormat();
		d.applyPattern(format);
		Date nowdate = new Date();
		String str_date = d.format(nowdate);
		return str_date;
	}


	public static String myTimeStr(long timeMillis) {
		int timezone = 8;
		long totalSeconds = timeMillis / 1000;
		totalSeconds += 60 * 60 * timezone;
		int second = (int) (totalSeconds % 60);// 秒
		long totalMinutes = totalSeconds / 60;
		int minute = (int) (totalMinutes % 60);// 分
		long totalHours = totalMinutes / 60;
		int hour = (int) (totalHours % 24);// 时
		int totalDays = (int) (totalHours / 24);

		int _year = 1970;

		int year = _year + totalDays / 366;
		int month = 1;
		int day = 1;

		int diffDays;
		boolean leapYear;
		while (true) {
			int diff = (year - _year) * 365;
			diff += (year - 1) / 4 - (_year - 1) / 4;
			diff -= ((year - 1) / 100 - (_year - 1) / 100);
			diff += (year - 1) / 400 - (_year - 1) / 400;

			diffDays = totalDays - diff;

			leapYear = (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);
			if (!leapYear && diffDays < 365 || leapYear && diffDays < 366) {
				break;
			} else {
				year++;
			}
		}

		int[] monthDays;
		if (diffDays >= 59 && leapYear) {
			monthDays = new int[] { -1, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335 };
		} else {
			monthDays = new int[] { -1, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
		}
		for (int i = monthDays.length - 1; i >= 1; i--) {
			if (diffDays >= monthDays[i]) {
				month = i;
				day = diffDays - monthDays[i] + 1;
				break;
			}
		}
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

	}
	
	
  
    public static String format(Date date) {  
        long delta = new Date().getTime() - date.getTime();  
        if (delta < 1L * ONE_MINUTE) {  
            long seconds = toSeconds(delta);  
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;  
        }  
        if (delta < 45L * ONE_MINUTE) {  
            long minutes = toMinutes(delta);  
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;  
        }  
        if (delta < 24L * ONE_HOUR) {  
            long hours = toHours(delta);  
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;  
        }  
        if (delta < 48L * ONE_HOUR) {  
            return "YESTERDAY";  
        }  
        if (delta < 30L * ONE_DAY) {  
            long days = toDays(delta);  
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;  
        }  
        if (delta < 12L * 4L * ONE_WEEK) {  
            long months = toMonths(delta);  
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;  
        } else {  
            long years = toYears(delta);  
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;  
        }  
    }  
  
    private static long toSeconds(long date) {  
        return date / 1000L;  
    }  
  
    private static long toMinutes(long date) {  
        return toSeconds(date) / 60L;  
    }  
  
    private static long toHours(long date) {  
        return toMinutes(date) / 60L;  
    }  
  
    private static long toDays(long date) {  
        return toHours(date) / 24L;  
    }  
  
    private static long toMonths(long date) {  
        return toDays(date) / 30L;  
    }  
  
    private static long toYears(long date) {  
        return toMonths(date) / 365L;  
    }  
  

	/**
	 *	 
	  *	时间天数相差
	  * @Title: DateDiff
	  * @Description: TODO
	  * @param @param start_date
	  * @param @param end_date
	  * @param @return 
	  * @return int
	  * @throws
	 */
	private   int DateDiff(String start_date, String end_date) {
		return DateDiff(StrToDate(start_date), StrToDate(end_date));
	}
	/**
	 *  
	  * timeCompare(比较两个时间)
	  * @Title: timeCompare
	  * @Description: TODO
	  * @Param @param start_date
	  * @Param @param end_date
	  * @Param @return    设定文件
	  * @Return boolean    返回类型
	  * @Throws
	 */
	public static Integer timeCompare(String start_date, String end_date){
		Integer result=null;
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		try{
			c1.setTime(df.parse(start_date));
			c2.setTime(df.parse(end_date));
			result=c1.compareTo(c2);
			if(result==0)
				System.out.println("start_date相等end_date");
			else if(result<0)
				System.out.println("start_date小于end_date");
			else
				System.out.println("start_date大于end_date");
			
		}catch(ParseException e){
			System.err.println("格式不正确");
		}
		return result;
	}
	
	
	
	public static Date minuteAdd(Object date,int minute){
		
		Date newDate=null;
		
		if(date instanceof Date){
			newDate=(Date) date;
		}else{
			newDate=parseDateFormat(date.toString());
		}

		return new Date(((Date)newDate).getTime() + minute*60000);
	}
	
	public static void main(String[] args) throws ParseException {  

       
       System.out.println(getDate());
  }  
}
