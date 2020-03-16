package com.sim.andon.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 
 * 项目名称：Kod 包名 com.kod.utils 类名称：DateUtils 类描述： -== 日期工具类 ==- 创建人：yuanqi.jing
 * 创建时间：2014-11-11 上午8:54:56 修改备注：
 * 
 * @version 1.0
 * 
 */
public class DateUtils {

	protected static final Logger log = LoggerFactory.getLogger(DateUtils.class);

	// 将天转换成微秒
	public static final long DAY_TIME = 24 * 60 * 60 * 1000;
	public static final TimeZone TZ = TimeZone.getDefault();

	/**
	 * 取得当前日期字符串，格式自定义
	 * 
	 * @param dateFormat
	 * @return String
	 */
	public static String getDateTime(String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 取得当前日期字符串，格式："yyyy-MM-dd"
	 * 
	 * @return String
	 */
	public static String getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	/**
	 * 取得当前月份字符串，格式："yyyy-MM"
	 * 
	 * @return String
	 */
	public static String getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		return df.format(date);
	}

	/**
	 * 取得当前日期字符串，格式："yyyy"
	 * 
	 * @return String
	 */
	public static String getDateYear(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String dateStr = df.format(date);
		return dateStr;
	}

	/**
	 * 取得当前日期前一天的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDay() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 取得当前日期前一天的日期字符串，格式："yyyy-MM-dd"
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastDayStr() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	/**
	 * 取得日期前一天的日期字符串，格式："yyyy-MM-dd"
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastDayStr(String dateStr) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			log.error("getLastDayStr()日期转换异常{}" + e);
		}
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return df.format(date);
	}

	/**
	 * 取得日期的相对的日期字符串，格式："yyyy-MM-dd"
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateStr(String dateStr, int dayNum) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			log.error("getLastDayStr()日期转换异常{}" + e);
		}
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayNum);
		date = calendar.getTime();
		return df.format(date);
	}

	/**
	 * 转换日历对象为日期字符串
	 * 
	 * @author xjb
	 * @param Calendar
	 *            字符串
	 * @return Date 日期（yyyy-MM-dd HH:mm:ss）
	 */
	public static Date convertCalToDate(Calendar da) {
		Date date = da.getTime();
		return date;
	}

	/**
	 * 取得当前日期字符串，格式："yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return String
	 */
	public static String getCurrentDateTime() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currDate = df.format(date);
		return currDate;
	}

	/**
	 * 取得当前日期字符串，格式："yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return String
	 */
	public static String getDateTime(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(date);
		return dateStr;
	}

	/**
	 * 取得当前日期字符串，格式："yyyy-MM-dd"
	 * 
	 * @return String
	 */
	public static String getDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(date);
		return dateStr;
	}

	/**
	 * 取得当前日期字符串，格式："yyyy-MM"
	 * 
	 * @return String
	 */
	public static String getDateMonth(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String dateStr = df.format(date);
		return dateStr;
	}

	/**
	 * 取得当前日期字符串，格式："HH:mm:ss"
	 * 
	 * @return String
	 */
	public static String getTime(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		String dateStr = df.format(date);
		return dateStr;
	}

	/**
	 * 转换日历对象为时间对象
	 * 
	 * @param cal
	 * @return Date
	 */
	public static Date convertCalendarToDate(Calendar cal) {
		long day = cal.getTimeInMillis();
		day = (day + TZ.getOffset(day)) / DAY_TIME;
		day = day * DAY_TIME;
		return new Date(day - TZ.getOffset(day));
	}

	/**
	 * 按天比较两个时间对象的大小
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static int compareDay(Date arg0, Calendar arg1) {
		int firstDay = (int) ((arg0.getTime() + TZ.getOffset(arg0.getTime())) / DAY_TIME);
		int secendDay = (int) ((arg1.getTimeInMillis() + TZ.getOffset(arg0.getTime())) / DAY_TIME);
		return firstDay - secendDay;
	}

	/**
	 * 计算两个时间对象相差的天数
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static int calcWorkdayCount(Date arg0, Date arg1) {
		if (arg0 == null || arg1 == null)
			return 0;
		int firstDay = (int) (arg0.getTime() / DAY_TIME);
		int secendDay = (int) (arg1.getTime() / DAY_TIME);
		return (firstDay - secendDay) + 1;
	}

	/**
	 * 累加日期对象的时间
	 * 
	 * @param val
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date addDate(Date val, int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(val);
		if (year != 0)
			cal.add(Calendar.YEAR, year);
		if (month != 0)
			cal.add(Calendar.MONTH, month);
		if (day != 0)
			cal.add(Calendar.DATE, day);
		return convertCalendarToDate(cal);
	}

	/**
	 * 比较当前时间在指定时间段是否过半
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static double compareDayToHalf(Date arg0, Date arg1, Calendar arg2) {
		double firstDay = arg0.getTime() / DAY_TIME;
		double secendDay = arg1.getTime() / DAY_TIME;
		double current = arg2.getTimeInMillis() / DAY_TIME;
		return ((secendDay - firstDay + 1) / 2 + firstDay) - current;
	}

	/**
	 * 
	 * @Title：getDayNum
	 * @Description: TODO
	 * @date 2015年2月11日 上午9:48:05
	 * @param dateStr
	 *            时间
	 * @return 此月的天数
	 */
	public static int getDayNum(String dateStr) {
		Calendar c = Calendar.getInstance();// 获得一个日历的实例
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.setTime(date);// 设置日历时间
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
		// int dayNum = c.get(Calendar.DAY_OF_MONTH);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	// 返回当前日期,类型为格式"yyyy-mm-dd"的字符串
	public static synchronized String getDateByFormat(String format) {
		SimpleDateFormat d = new SimpleDateFormat();
		d.applyPattern(format);
		Date nowdate = new Date();
		String str_date = d.format(nowdate);
		return str_date;
	}

	/**
	 * @Title：getIntTime
	 * @Description: 设置时间的分、秒、毫秒为0
	 * @author yuanqi.jing
	 * @date 2016年7月25日 下午4:11:50
	 * @param timestamp
	 * @return
	 */
	public static Timestamp getIntTime(Timestamp timestamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * @Title：getNextIntTime
	 * @Description: 获取下一个整点
	 * @author yuanqi.jing
	 * @date 2016年7月25日 下午4:11:50
	 * @param timestamp
	 * @return
	 */
	public static Timestamp getNextIntTime(Timestamp timestamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp);
		cal.add(Calendar.HOUR_OF_DAY, 1);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * @Title：getNextIntTime
	 * @Description: 获取下一个整点
	 * @author yuanqi.jing
	 * @date 2016年7月25日 下午4:11:50
	 * @param timestamp
	 * @return
	 */
	public static String getNextMonth(String month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getDateFromString(month + "-01", "yyyy-MM-dd"));
		cal.add(Calendar.MONTH, 1);
		return getDateMonth(cal.getTime());
	}

	public static String getNextWeek(String dateShow) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getDateFromString(dateShow, "yyyy-MM-dd"));
		cal.add(Calendar.DAY_OF_MONTH, 7);
		return getDate(cal.getTime());
	}

	public static String getNextDay(String dateShow) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getDateFromString(dateShow, "yyyy-MM-dd"));
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return getDate(cal.getTime());
	}

	public static String getNextYear(String dateShow) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getDateFromString(dateShow, "yyyy"));
		cal.add(Calendar.YEAR, 1);
		return getDateYear(cal.getTime());
	}

	/**
	 * @Title：getDateStrFromTimestamp
	 * @Description: 获得时间戳的日期字符串，格式："yyyy-MM-dd"
	 * @author yuanqi.jing
	 * @date 2016年7月25日 下午5:04:24
	 * @param t
	 * @return
	 */
	public static String getDateStrFromTimestamp(Timestamp t) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(t);
		Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	/**
	 * @Title：timeT
	 * @Description: 两个时间戳间隔的小时数
	 * @author yuanqi.jing
	 * @date 2016年7月25日 下午5:33:30
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static int getHoursBetween(Timestamp t1, Timestamp t2) {
		int hours = (int) ((t1.getTime() - t2.getTime()) / 3600000);
		return Math.abs(hours);
	}

	/**
	 * 
	 * @Title：getDateStrFromTimestamp2
	 * @Description: 获得时间戳的日期字符串，格式："yyyy-MM-dd HH:mm:ss"
	 * @author
	 * @date 2016年8月1日 上午11:23:07
	 * @param t
	 * @return
	 */
	public static String getDateStrFromTimestamp2(Timestamp t) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(t);
		Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * 
	 * @Title：getDateStrFromTimestampStr
	 * @Description: 时间戳字符串转为 yyyy-MM-dd HH:mm:ss格式的字符串
	 * @author
	 * @date 2016年8月1日 下午2:54:29
	 * @param timestampStr
	 * @return
	 */
	public static String getDateStrFromTimestampStr(String timestampStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long unixLong = 0;
		unixLong = Long.parseLong(timestampStr) * 1000;
		String d = format.format(unixLong);
		return d;
	}

	/**
	 * @Title：isHourEq
	 * @Description: 判断两个时间戳的小时数是否相同 (只判断小时，日期忽略)
	 * @author yuanqi.jing
	 * @date 2016年8月23日 上午10:44:55
	 * @param timestamp1
	 * @param timestamp2
	 * @return
	 */
	public static boolean isHourEq(Timestamp timestamp1, Timestamp timestamp2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(timestamp1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(timestamp2);

		int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
		int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
		return hour1 == hour2;
	}

	/**
	 * 字符串转换为Date，格式："yyyy-MM-dd"
	 * 
	 * @return String
	 */
	public static Date getDateFromString(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			String dateStr = sdf.format(date);
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 两日期类型字符串 时间相差时间
	 * 
	 * @Title：getTimeConsuming
	 * @Description: TODO
	 * @author rui.han
	 * @date 2016年9月29日 上午11:20:04
	 * @param beginDateStr
	 * @param endDateStr
	 * @return String xx天xx小时xx分
	 */
	public static String getTimeConsuming(Date beginDate, Date endDate) {
		// Date beginDate = getDateFromString(beginDateStr, "yyyy-MM-dd
		// HH:mm:ss");
		// Date endDate = getDateFromString(endDateStr, "yyyy-MM-dd HH:mm:ss");
		long day = 0;
		long hour = 0;
		long min = 0;
		// long sec = 0;
		long diff = endDate.getTime() - beginDate.getTime();
		day = diff / (24 * 60 * 60 * 1000);
		hour = (diff / (60 * 60 * 1000) - day * 24);
		min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		// sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		StringBuffer timeConsuming = new StringBuffer();
		if (day != 0L) {
			timeConsuming.append(day + "天");
		}
		if (hour != 0L) {
			timeConsuming.append(hour + "小时");
		}
		if (min != 0L) {
			timeConsuming.append(min + "分");
		}
		// if (sec != 0L) {
		// timeConsuming.append(sec + "秒");
		// }
		return timeConsuming.toString();
	}

	/**
	 * 两日期类型字符串 时间相差时间
	 * 
	 * @Title：getDuration
	 * @Description: TODO
	 * @author rui.han
	 * @date 2017年2月8日 下午5:39:16
	 * @param beginDate
	 * @param endDate
	 * @return String 时：分：秒
	 */
	public static String getDuration(Date beginDate, Date endDate) {
		// Date beginDate = getDateFromString(beginDateStr, "yyyy-MM-dd
		// HH:mm:ss");
		// Date endDate = getDateFromString(endDateStr, "yyyy-MM-dd HH:mm:ss");
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		long diff = endDate.getTime() - beginDate.getTime();
		day = diff / (24 * 60 * 60 * 1000);
		hour = (diff / (60 * 60 * 1000) - day * 24);
		min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		StringBuffer timeConsuming = new StringBuffer();
		if (hour != 0L) {
			timeConsuming.append(hour + ":");
		} else {
			timeConsuming.append("0" + ":");
		}
		if (min != 0L) {
			timeConsuming.append(min + ":");
		} else {
			timeConsuming.append("00" + ":");
		}
		if (sec != 0L) {
			timeConsuming.append(sec);
		} else {
			timeConsuming.append("00");
		}
		return timeConsuming.toString();
	}

	/**
	 * @Title：getWeekName
	 * @Description: 获取星期
	 * @author yuanqi.jing
	 * @date 2016年11月10日 下午3:58:35
	 * @param cal
	 * @return
	 */
	public static String getWeekName(Calendar cal) {
		int weekDay = cal.get(Calendar.DAY_OF_WEEK);
		String weekName = "";
		switch (weekDay) {
		case Calendar.SUNDAY:
			weekName = "星期日";
			break;
		case Calendar.MONDAY:
			weekName = "星期一";
			break;
		case Calendar.TUESDAY:
			weekName = "星期二";
			break;
		case Calendar.WEDNESDAY:
			weekName = "星期三";
			break;
		case Calendar.THURSDAY:
			weekName = "星期四";
			break;
		case Calendar.FRIDAY:
			weekName = "星期五";
			break;
		case Calendar.SATURDAY:
			weekName = "星期六";
			break;

		default:
			break;
		}
		return weekName;
	}

	/**
	 * 得到本周日期，n=1为周一
	 * 
	 * @return Date
	 */
	public static Calendar getWeekDateOfThisWeek(int n) {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + n);
		return c;
	}

	/**
	 * 得到本周日期字符串，n=1为周一
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getWeekDateStrOfThisWeek(int n) {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + n);
		return getDate(c.getTime());
	}

	/**
	 * 得到本周周一
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return getDate(c.getTime());
	}

	/**
	 * 得到本周周日
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return getDate(c.getTime());
	}

	// 七周前周一
	public static String getMondayOf7WeekBefor() {
		String paramStartDate = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date dateNow = new Date();
		Date dateBefore = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(dateNow);
		cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, -6);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		dateBefore = cal.getTime();
		paramStartDate = sdf.format(dateBefore);
		return paramStartDate;
	}

	/**
	 * 获取当月的 天数
	 */
	public static int getCurrentMonthDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * @Title：getCurrMonthDateStr
	 * @Description: 获取当月第一天字符串，格式："yyyy-MM-dd"
	 * @author yuanqi.jing
	 * @date 2016年11月11日 上午11:19:42
	 * @return
	 */
	public static String getCurrMonthFirstDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = format.format(cal.getTime());
		return firstDay;

	}

	/**
	 * @Title：getCurrMonthDateStr
	 * @Description: 获取当月最后一天字符串，格式："yyyy-MM-dd"
	 * @author yuanqi.jing
	 * @date 2016年11月11日 上午11:19:42
	 * @return
	 */
	public static String getCurrMonthLastDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		String lastDay = format.format(cal.getTime());
		return lastDay;
	}

	/**
	 * 每月的天数
	 * 
	 * @return
	 */
	public static String[] getArrayData(String startTimeStr, String endTimeStr, String type) {
		int maxDate = 0;
		int start = 0;
		int count = 0;
		switch (type) {
		case "0":// 月
			maxDate = getDayNum(startTimeStr);
			start = 1;
			count = maxDate;
			break;
		case "1":// 周
			return getWeekOfDay(endTimeStr);
		case "2":// 日
			maxDate = 24;
			start = 0;
			count = maxDate;
			break;
		default:
			break;
		}
		String[] strArray = new String[count];
		for (int i = 0; i < count; i++) {
			strArray[i] = start + "";
			start++;
		}
		return strArray;
	}

	private static String[] getWeekOfDay(String endTimeStr) {
		String[] strArray = new String[7];

		String strArray6 = getLastDayStr(endTimeStr);
		String strArray5 = getLastDayStr(strArray6);
		String strArray4 = getLastDayStr(strArray5);
		String strArray3 = getLastDayStr(strArray4);
		String strArray2 = getLastDayStr(strArray3);
		String strArray1 = getLastDayStr(strArray2);

		strArray[6] = endTimeStr.split("-")[2];
		strArray[5] = strArray6.split("-")[2];
		strArray[4] = strArray5.split("-")[2];
		strArray[3] = strArray4.split("-")[2];
		strArray[2] = strArray3.split("-")[2];
		strArray[1] = strArray2.split("-")[2];
		strArray[0] = strArray1.split("-")[2];
		return strArray;
	}

	/**
	 * 计算两个日期（字符串）相差的天数
	 * 
	 * @param dateStr1
	 *            yyyy-MM-dd
	 * @param dateStr2
	 *            yyyy-MM-dd
	 * @return
	 */
	public static int diffDaysBetween(String dateStr1, String dateStr2) {
		// Calendar cal1 = Calendar.getInstance();
		// Calendar cal2 = Calendar.getInstance();
		// try {
		// cal1.setTime((new SimpleDateFormat("yyyy-MM-dd").parse(dateStr1)));
		// cal2.setTime((new SimpleDateFormat("yyyy-MM-dd").parse(dateStr2)));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// System.out.println("日期格式错误");
		// }
		// return cal1.compareTo(cal2);
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr1);
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr2);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("日期格式错误");
		}
		long betweenDays = (date1.getTime() - date2.getTime()) / (1000 * 3600 * 24);
		return Math.abs(Integer.parseInt(String.valueOf(betweenDays)));
	}

	/**
	 * 根据“yyyy-MM”得到本月最后一天的“yyyy-MM-dd”
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getTheMonthLastday(String dateStr) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf1.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("日期转换异常");
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		String lastDay = sdf2.format(cal.getTime());
		return lastDay;
	}

	/**
	 * 计算两个日期（字符串）相差的月数
	 * 
	 * @param startDate
	 *            yyyy-MM-dd
	 * @param endDate
	 *            yyyy-MM-dd
	 * @return
	 */
	// public static int getMonthsBetween(String startDate, String endDate) {
	// Date date1 = null;
	// Date date2 = null;
	// try {
	// date1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
	// date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
	// } catch (ParseException e) {
	// e.printStackTrace();
	// System.out.println("日期格式错误");
	// }
	// long diffMonths = (date1.getTime() - date2.getTime())/(1000*3600*24);
	// return Math.abs(Integer.parseInt(String.valueOf(betweenDays)));
	// }

	/**
	 * 计算两个日期（字符串）相差的年数
	 * 
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws ParseException
	 */
	public static int getYearBetween(String minDate, String maxDate) throws ParseException {
		Date start = getDateFromString(minDate, "yyyy");
		Date end = getDateFromString(maxDate, "yyyy");
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(start);
		max.setTime(end);
		int year1 = min.get(Calendar.YEAR);
		int year2 = max.get(Calendar.YEAR);
		int year = Math.abs(year1 - year2);
		return year;

	}

	/**
	 * 计算两个日期（字符串）相差的月数
	 * 
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws ParseException
	 */
	public static int getMonthBetween(String minDate, String maxDate) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}

		int months = 0;
		if (result != null && result.size() > 0) {
			months = result.size();
		}
		return months;
	}

	public static int getWeekBetween(String startDate, String endDate) {
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			// Date start = (Date) startDate;
			// Date end = (Date) endDate;
			// Calendar cal = Calendar.getInstance();
			// cal.setTime(start);
			// long time1 = cal.getTimeInMillis();
			// cal.setTime(end);
			// long time2 = cal.getTimeInMillis();
			// long between_days = (time2 - time1) / (1000 * 3600 * 24);

			int between_days = DateUtils.diffDaysBetween(startDate, endDate);
			Double days = Double.parseDouble(String.valueOf(between_days));
			if ((days / 7) > 0 && (days / 7) <= 1) {
				// 不满一周的按一周算
				return 1;
			} else if (days / 7 > 1) {
				int day = days.intValue();
				if (day % 7 > 0) {
					return day / 7 + 1;
				} else {
					return day / 7;
				}
			} else if ((days / 7) == 0) {
				return 0;
			} else {
				// 负数返还null
				return 0;
			}
		}
		return 0;
	}

	public static String getYearWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置周一为一周的第一天
		cal.setMinimalDaysInFirstWeek(4);

		// 设置一年的第一周至少包含4天，否则算作上一年的53周，这与oracle的周日期格式：iyyy-iw对应
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		// System.out.println(week);

		// 如果当前周数比一周前的周数小，说明已经跨年，年数加1
		cal.add(Calendar.DAY_OF_MONTH, -7);
		int year = cal.get(Calendar.YEAR);
		if (week < cal.get(Calendar.WEEK_OF_YEAR)) {
			year += 1;
		}
		String weekStr = "";
		if (week < 10) {
			weekStr = year + "-" + "0" + week;
		} else {
			weekStr = year + "-" + week;
		}
		return weekStr;
	}

	/*
	 * public static void main(String[] args) { // String date1 = "2017-01-02";
	 * // String date2 = "2017-01-15"; // // System.out.println("diff:" +
	 * DateUtils.diffDaysBetween(date1, // date2));
	 * 
	 * // System.out.println(getTheMonthLastday("2017-01"));
	 * 
	 * // try { // int months = getMonthBetween("2016-05", "2017-03"); // Gson
	 * gson = new Gson(); // System.out.println(gson.toJson(months)); // } catch
	 * (ParseException e) { // // TODO Auto-generated catch block //
	 * e.printStackTrace(); // }
	 * 
	 * // String dateStr = "2017-01-01";
	 * 
	 * 
	 * String dateStr = "2014-01-01"; // String dateStr = "2000-01-01";
	 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); Date date =
	 * null; try { date = df.parse(dateStr); } catch (ParseException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * String result = DateUtils.getYearWeek(date); System.out.println("result："
	 * + result); // System.out.println("yyyy-周：" + result);
	 * 
	 * // Map<String, Object> param = new HashMap<String, Object>(); //
	 * param.put("p1","abc"); // Gson g = new Gson(); // String result1=
	 * g.toJson(param); // System.out.println("param:" + result1); // //
	 * Map<String, Object> map = new HashMap<String, Object>(); // map =
	 * g.fromJson(result1, map.getClass()); // String p1 =
	 * map.get("p1").toString(); // System.out.println("p1:" + p1);
	 * 
	 * }
	 */

	/**
	 * 取得当前日期字符串，格式："yyyy"
	 * 
	 * @return String
	 */
	public static String getCurrYear() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String dateStr = df.format(new Date());
		return dateStr;
	}

	public static String get6YearBefor() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -5);
		Date y = c.getTime();
		String year = format.format(y);
		return year;
	}

	/**
	 * @Title：diffMinutes
	 * @Description: 两个日期间隔的分钟数
	 * @author yuanqi.jing
	 * @date 2017年3月29日 下午3:51:50
	 * @param errorTime
	 * @param date
	 * @return
	 */
	public static long diffMinutes(Date errorTime, Date date) {
		long ms1 = errorTime.getTime();
		long ms2 = date.getTime();
		long minutes = Math.abs((ms2 - ms1) / (1000 * 60));// 转化minute
		return minutes;
	}

	public static String[] getArrayDataStr(String startTimeStr, String endTimeStr, String type) {
		try {
			Calendar cal = Calendar.getInstance();
			// x轴显示的日期名称
			String dateShow;
			String[] x = null;
			switch (type) {
			case "day":
				cal.setTime(DateUtils.getDateFromString(startTimeStr, "yyyy-MM-dd"));
				int diffDays = DateUtils.diffDaysBetween(startTimeStr, endTimeStr);
				x = new String[diffDays + 1];
				for (int i = 0; i < (diffDays + 1); i++) {
					dateShow = DateUtils.getDate(cal.getTime());
					x[i] = dateShow;
					// 下一天
					cal.add(Calendar.DAY_OF_MONTH, 1);
				}
				break;

			case "week":
				cal.setTime(DateUtils.getDateFromString(startTimeStr, "yyyy-MM-dd"));
				int weeks = DateUtils.getWeekBetween(startTimeStr, endTimeStr);
				x = new String[weeks];
				for (int i = 0; i < weeks; i++) {
					// 获得日
					dateShow = DateUtils.getYearWeek(cal.getTime());
					x[i] = dateShow;
					// 下一周
					cal.add(Calendar.DAY_OF_MONTH, 7);
				}
				break;
			case "month":
				startTimeStr = startTimeStr + "-01";
				endTimeStr = DateUtils.getTheMonthLastday(endTimeStr);
				cal.setTime(DateUtils.getDateFromString(startTimeStr, "yyyy-MM-dd"));
				int months = DateUtils.getMonthBetween(startTimeStr, endTimeStr);
				x = new String[months];
				for (int i = 0; i < months; i++) {
					// 获得日
					dateShow = DateUtils.getDateMonth(cal.getTime());
					x[i] = dateShow;
					// 下一月
					cal.add(Calendar.MONTH, 1);
				}
				break;
			case "year":
				cal.setTime(DateUtils.getDateFromString(startTimeStr, "yyyy"));
				int years = DateUtils.getYearBetween(startTimeStr, endTimeStr);
				x = new String[years + 1];
				for (int i = 0; i < years + 1; i++) {
					// 获得日
					dateShow = DateUtils.getDateYear(cal.getTime());
					x[i] = dateShow;
					// 下一年
					cal.add(Calendar.YEAR, 1);
				}
				break;
			default:
				break;
			}
			return x;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// public static void main(String[] args) {
	// Calendar cal1 = Calendar.getInstance();
	// Calendar cal2 = Calendar.getInstance();
	// cal1.add(Calendar.MINUTE, -25);
	// long minutes = DateUtils.diffMinutes(cal1.getTime(), cal2.getTime());
	// System.out.println(minutes);
	// }
}
