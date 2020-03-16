package com.sim.andon.web.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by fangang on 2017/2/7. 班次计算工具类
 */
public class ShiftUtils {

	/* 当前班次开始时间 */
	public static final String SHIFT_TIME;
	/* 上一个班次开始时间 */
	public static final String PRE_SHIFT_TIME;
	/* 当前班次结束时间 */
	public static final String END_TIME;

	/* 当前班次开始日期 */
	public static final String SHIFT_DATE;
	/* 上一个班次开始日期 */
	public static final String PRE_SHIFT_DATE;
	/* 当前班次结束日期 */
	public static final String END_DATE;

	/* 当前班次开始小时 */
	public static final String SHIFT_HOUR;
	/* 上一个班次开始小时 */
	public static final String PRE_SHIFT_HOUR;
	/* 当前班次结束小时 */
	public static final String END_HOUR;

	static {
		PRE_SHIFT_TIME = "pre_shift_time";
		SHIFT_TIME = "shift_time";
		END_TIME = "end_time";

		SHIFT_DATE = "shift_date";
		PRE_SHIFT_DATE = "pre_shift_date";
		END_DATE = "end_date";

		SHIFT_HOUR = "shift_hour";
		PRE_SHIFT_HOUR = "pre_shift_hour";
		END_HOUR = "end_hour";

	}

	public static void main(String[] args) {

		HashMap<String, String> shiftMap = ShiftUtils.getShiftTimeByDate();

		System.out.println(shiftMap.get(PRE_SHIFT_TIME));
		System.out.println(shiftMap.get(SHIFT_TIME));
		System.out.println(shiftMap.get(END_TIME));

		System.out.println(shiftMap.get(SHIFT_DATE));
		System.out.println(shiftMap.get(PRE_SHIFT_DATE));
		System.out.println(shiftMap.get(END_DATE));

		System.out.println(shiftMap.get(SHIFT_HOUR));
		System.out.println(shiftMap.get(PRE_SHIFT_HOUR));
		System.out.println(shiftMap.get(END_HOUR));
	}

	/**
	 *
	 * @return
	 */
	public static HashMap<String, String> getShiftTimeByDate() {

		HashMap<String, String> result = new HashMap<>();

		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

		DateTime dateTime = DateTime.now();
		// DateTime dateTime = new DateTime(2017,4,20,1,12,15);

		Integer nowHours = dateTime.getHourOfDay();

		String nowShiftTime = null;
		String preShiftTime = null;
		String nowEndTime = null;

		String nowShiftDate = null;
		String preShiftDate = null;
		String nowEndDate = null;

		String nowShiftHour = null;
		String preShiftHour = null;
		String nowEndHour = null;

		if (nowHours >= 7 && nowHours < 19) {
			// 白班，计算当前班次开始时间及上一班次开始时间

			nowShiftTime = dateTime.toString("yyyy-MM-dd") + " 07:00:00";
			nowShiftDate = dateTime.toString("yyyy-MM-dd");
			nowShiftHour = "7";

		} else {
			// 夜班
			nowShiftHour = "19";
			if (nowHours < 7) {
				// 凌晨
				// 计算当前班次开始时间，前一天的19点开始
				nowShiftTime = dateTime.minusDays(1).toString("yyyy-MM-dd") + " 19:00:00";
				nowShiftDate = dateTime.minusDays(1).toString("yyyy-MM-dd");
			} else {
				// 后半夜
				nowShiftTime = dateTime.toString("yyyy-MM-dd") + " 19:00:00";
				nowShiftDate = dateTime.toString("yyyy-MM-dd");

			}
		}

		DateTime preDt = DateTime.parse(nowShiftTime, format);
		// 上一个班次开始时间
		preShiftTime = preDt.minusHours(12).toString("yyyy-MM-dd HH:mm:ss");
		preShiftDate = preDt.minusHours(12).toString("yyyy-MM-dd");
		preShiftHour = preDt.minusHours(12).toString("HH");
		// 当前班次结束时间
		nowEndTime = preDt.plusHours(12).toString("yyyy-MM-dd HH:mm:ss");
		nowEndDate = preDt.plusHours(12).toString("yyyy-MM-dd");
		nowEndHour = preDt.plusHours(12).toString("HH");

		result.put(PRE_SHIFT_TIME, preShiftTime);
		result.put(SHIFT_TIME, nowShiftTime);
		result.put(END_TIME, nowEndTime);

		result.put(SHIFT_DATE, nowShiftDate);
		result.put(PRE_SHIFT_DATE, preShiftDate);
		result.put(END_DATE, nowEndDate);

		result.put(SHIFT_HOUR, nowShiftHour);
		result.put(PRE_SHIFT_HOUR, preShiftHour);
		result.put(END_HOUR, nowEndHour);

		return result;
	}

	public static String[] getArrayData(String type) {
		HashMap<String, String> map = getShiftTimeByDate();
		String starttime = null;
		String endtime = null;
		Date start = null;
		Date end = null;
		int startInt = 0;
		int endInt = 0;
		if (type.equals("this")) {// 查询当前班次
			starttime = map.get(ShiftUtils.SHIFT_TIME);/* 当前班次开始时间 */
			start = DateUtil.parseDateFormat(starttime, "yyyy-MM-dd HH:mm:ss");
			startInt = start.getHours();
			endtime = map.get(ShiftUtils.END_TIME);/* 当前班次结束时间 */
			end = DateUtil.parseDateFormat(endtime, "yyyy-MM-dd HH:mm:ss");
			endInt = end.getHours();
		} else {
			starttime = map.get(ShiftUtils.PRE_SHIFT_TIME);/* 上个班次开始时间 */
			start = DateUtil.parseDateFormat(starttime, "yyyy-MM-dd HH:mm:ss");
			startInt = start.getHours();
			endtime = map.get(ShiftUtils.SHIFT_TIME);/* 当前班次开始时间 */
			end = DateUtil.parseDateFormat(endtime, "yyyy-MM-dd HH:mm:ss");
			endInt = end.getHours();
		}

		String[] strArray = new String[12];
		for (int i = 0; i < 12; i++) {
			if (startInt == 24) {
				startInt = 0;
			}
			strArray[i] = startInt + "";
			startInt++;
		}
		return strArray;
	}
}
