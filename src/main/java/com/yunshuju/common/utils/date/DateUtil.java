package com.yunshuju.common.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunshuju.common.utils.MeStringUtils;
import com.yunshuju.common.utils.exception.IllegalDataException;
import com.yunshuju.common.utils.exception.MeException;

/**
 * 日期工具类
 */
public class DateUtil {
	private DateUtil() {
	}

	private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	private static final long MILLISECOND_OF_DAY = 24 * 60 * 60 * 1000;

	/**
	 * 日期(date)格式 yyyy-MM-dd
	 */
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	/**
	 * 日期(date)格式 yyyy年MM月dd日
	 */
	public static final String FORMAT_DATE_CH = "yyyy年MM月dd日";
	/**
	 * 　时间(datetime)格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	/**
	 * mysql中所能表达的最小时间
	 */
	private static Date minDate;
	/**
	 * mysql中所能表达的最大时间
	 */
	private static Date maxDate;

	public static final DateFormatThreadLocal TL_STD_DATE_FORMAT;
	public static final DateFormatThreadLocal TL_STD_TIME_FORMAT;
	public static final DateFormatThreadLocal TL_STD_DATE_TIME_FORMAT;

	public static final DateFormatThreadLocal TL_STD_SHORT_TIME_FORMAT;
	public static final DateFormatThreadLocal TL_STD_SHORT_DATE_TIME_FORMAT;
	public static final DateFormatThreadLocal TL_STD_TIMESTAMP_FORMAT;
	//
	public static final DateFormatThreadLocal TL_CMP_TIMESTAMP_FORMAT;
	public static final DateFormatThreadLocal TL_CMP_DATE_TIME_FORMAT;
	public static final DateFormatThreadLocal TL_CMP_DATE_FORMAT;
	public static final DateFormatThreadLocal TL_CMP_TIME_FORMAT;
	//
	public static final DateFormatThreadLocal TL_DATE_DIR_FORMAT;
	public static final DateFormatThreadLocal TL_DATE_FILE_FORMAT;
	public static final DateFormatThreadLocal TL_DATE_TIME_FILE_FORMAT;
	public static final DateFormatThreadLocal TL_TIMESTAMP_FILE_FORMAT;

	// 缓存日期格式
	private static Map<String, DateFormatThreadLocal> dateFormats = new HashMap<>();

	static {
		TL_STD_DATE_FORMAT = new DateFormatThreadLocal(DateFormats.STD_DATE);
		TL_STD_TIME_FORMAT = new DateFormatThreadLocal(DateFormats.STD_TIME);
		TL_STD_DATE_TIME_FORMAT = new DateFormatThreadLocal(DateFormats.STD_DATE_TIME);
		TL_STD_SHORT_TIME_FORMAT = new DateFormatThreadLocal(DateFormats.STD_SHORT_TIME);
		TL_STD_SHORT_DATE_TIME_FORMAT = new DateFormatThreadLocal(DateFormats.STD_SHORT_DATE_TIME);
		TL_STD_TIMESTAMP_FORMAT = new DateFormatThreadLocal(DateFormats.STD_TIMESTAMP);
		//
		TL_CMP_TIMESTAMP_FORMAT = new DateFormatThreadLocal(DateFormats.CMP_TIMESTAMP);
		TL_CMP_DATE_TIME_FORMAT = new DateFormatThreadLocal(DateFormats.CMP_DATE_TIME);
		TL_CMP_DATE_FORMAT = new DateFormatThreadLocal(DateFormats.CMP_DATE);
		TL_CMP_TIME_FORMAT = new DateFormatThreadLocal(DateFormats.CMP_TIME);
		//
		TL_DATE_DIR_FORMAT = new DateFormatThreadLocal(DateFormats.DATE_DIR_FORMAT);
		TL_DATE_FILE_FORMAT = new DateFormatThreadLocal(DateFormats.DATE_FILE_FORMAT);
		TL_DATE_TIME_FILE_FORMAT = new DateFormatThreadLocal(DateFormats.DATE_TIME_FILE_FORMAT);
		TL_TIMESTAMP_FILE_FORMAT = new DateFormatThreadLocal(DateFormats.TIMESTAMP_FILE_FORMAT);

		// 常见日期格式
		dateFormats.put(DateFormats.STD_DATE, TL_STD_DATE_FORMAT);
		dateFormats.put(DateFormats.STD_TIME, TL_STD_TIME_FORMAT);
		dateFormats.put(DateFormats.STD_DATE_TIME, TL_STD_DATE_TIME_FORMAT);
		dateFormats.put(DateFormats.STD_SHORT_TIME, TL_STD_SHORT_TIME_FORMAT);
		dateFormats.put(DateFormats.STD_SHORT_DATE_TIME, TL_STD_SHORT_DATE_TIME_FORMAT);
		dateFormats.put(DateFormats.STD_TIMESTAMP, TL_STD_TIMESTAMP_FORMAT);
		//
		dateFormats.put(DateFormats.CMP_TIMESTAMP, TL_CMP_TIMESTAMP_FORMAT);
		dateFormats.put(DateFormats.CMP_DATE_TIME, TL_CMP_DATE_TIME_FORMAT);
		dateFormats.put(DateFormats.CMP_DATE, TL_CMP_DATE_FORMAT);
		dateFormats.put(DateFormats.CMP_TIME, TL_CMP_TIME_FORMAT);
		//
		dateFormats.put(DateFormats.DATE_DIR_FORMAT, TL_DATE_DIR_FORMAT);
		dateFormats.put(DateFormats.DATE_FILE_FORMAT, TL_DATE_FILE_FORMAT);
		dateFormats.put(DateFormats.DATE_TIME_FILE_FORMAT, TL_DATE_TIME_FILE_FORMAT);
		dateFormats.put(DateFormats.TIMESTAMP_FILE_FORMAT, TL_TIMESTAMP_FILE_FORMAT);

		try {
			minDate = org.apache.commons.lang3.time.DateUtils.parseDate("1000-01-01 00:00:00", FORMAT_DATETIME);
			maxDate = org.apache.commons.lang3.time.DateUtils.parseDate("9999-12-31 23:59:59", FORMAT_DATETIME);
		} catch (ParseException e) {
			logger.error("生成日期异常", e);
		}
	}

	public static DateFormat getDateFormat(String format) {
		DateFormatThreadLocal threadLocal = dateFormats.get(format);
		if (null == threadLocal) {
			threadLocal = new DateFormatThreadLocal(format);
			dateFormats.put(format, threadLocal);
		}
		return threadLocal.get();
	}

	/**
	 * 获取mysql数据库能支持的最小时间
	 *
	 * @return
	 */
	public static Date getMinDate() {
		return minDate;
	}

	/**
	 * 获取mysql数据库能支持的最大时间
	 *
	 * @return
	 */
	public static Date getMaxDate() {
		return maxDate;
	}

	/**
	 * @param date
	 * @return String
	 * @Title: dateTimeToString
	 * @Description: 将时间转为字符串（格式为"yyyy-MM-dd HH:mm:ss"）
	 * @author yuyanhui
	 * @date 2013年7月10日 上午10:07:40
	 */
	public static String dateTimeToString(Date date) {
		return dateTimeToString(date, FORMAT_DATETIME);
	}

	/**
	 * @param date
	 * @param format
	 * @return String
	 * @Title: dateTimeToString
	 * @Description: 将时间转为字符串
	 * @author yuyanhui
	 * @date 2013年7月10日 上午10:08:50
	 */
	public static String dateTimeToString(Date date, String format) {
		if (null == date) {
			return "";
		}

		if (MeStringUtils.isEmpty(format)) {
			format = FORMAT_DATE;
		}
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 按默认yyyy-MM-dd格式转为日期
	 *
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String date) {
		return stringToDate(date, FORMAT_DATE);
	}

	/**
	 * @param date
	 * @param format
	 * @return Date
	 * @Title: stringToDate
	 * @Description:将日期格式的字符串转为日期
	 * @author yuyanhui
	 * @date 2013年7月10日 上午10:09:37
	 */
	public static Date stringToDate(String date, String format) {
		date = null == date ? null : date.trim();
		if (MeStringUtils.isEmpty(date)) {
			return null;
		}

		final String tmpFormat;
		if (MeStringUtils.isEmpty(format)) {
			tmpFormat = FORMAT_DATE;
		} else {
			tmpFormat = format;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(tmpFormat);
		try {
			Date cDate = simpleDateFormat.parse(date);
			verifyDateValidity(cDate);
			return cDate;
		} catch (Exception e) {
			if (e instanceof MeException) {
				throw (MeException) e;
			}
			String error = "将日期字符串转换为日期对象时出现异常，date = " + date + " / format = " + tmpFormat + "：";
			logger.error(error);
			logger.debug("", e);
		}
		return null;
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description:获取本周的第一天
	 * @author yuyanhui
	 */
	public static Date getCurrWeekFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description:获取本周的最后一天
	 * @author yuyanhui
	 */
	public static Date getCurrWeekLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}


	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description:获取上周的第一天
	 * @author yuyanhui
	 */
	public static Date getPrecedWeekFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description:获取上周的最后一天
	 * @author yuyanhui
	 */
	public static Date getPrecedWeekLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}


	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description: 获取本月第一天
	 * @author yuyanhui
	 */
	public static Date getCurrMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description: 获取本月的最后一天
	 * @author yuyanhui
	 */
	public static Date getCurrMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}


	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description: 获取上月第一天
	 * @author yuyanhui
	 */
	public static Date getPrecedMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description: 获取上月的最后一天
	 * @author yuyanhui
	 */
	public static Date getPrecedMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}


	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 * 获取后一天时间开始时间
	 */
	public static Date getTomorrowStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 * 获取后一天时间结束时间
	 */
	public static Date getTomorrowEnd() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}


	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 * 获取前一天时间开始时间
	 */
	public static Date getYesterdayStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 * 获取前一天时间结束时间
	 */
	public static Date getYesterdayEnd() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 * 获取今天开始时间
	 */
	public static Date getTodayDateStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 * 获取今天结束时间
	 */
	public static Date getTodayDateEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * @return
	 * @Description: 转换本地时间为UTC时间
	 * @author make(aloneboat@gmail.com)
	 */
	public static Date toUTCDate(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
			int dstOffset = cal.get(Calendar.DST_OFFSET);
			cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
			return cal.getTime();
		}
		return null;
	}

	/**
	 * @param d1
	 * @param d2
	 * @param format 日期的格式化：默认为"yyyy-MM-dd"
	 * @return int : 小于0表示的d1小于d2；等于0表示d1等于d2；大于0表示d1大于d2
	 * @Description: 比较两个日期的大小(d1、d2任意一个为null返回0)
	 * @author yuyanhui
	 */
	public static int compareDate(Date d1, Date d2, String format) {
		if (null == d1 && null == d2) {
			return 0;
		}
		String fm = format;
		if (MeStringUtils.isEmpty(fm)) {
			fm = "yyyy-MM-dd";
		}
		DateFormat df = new SimpleDateFormat(fm);
		if (null == d1) {
			d1 = d2;
		}
		if (null == d2) {
			d2 = d1;
		}
		String d1Str = df.format(d1);
		String d2Str = df.format(d2);
		return d1Str.compareToIgnoreCase(d2Str);
	}

	/**
	 * @param d1
	 * @param d2
	 * @param format 日期的格式化：默认为"yyyy-MM-dd"
	 * @return
	 * @Description: 判断d1是否大于等于d2
	 * @author yuyanhui
	 */
	public static boolean compareTwoDateGreaterOrEqual(Date d1, Date d2, String format) {
		return compareDate(d1, d2, format) >= 0;
	}

	/**
	 * @param d1
	 * @param d2
	 * @param format 日期的格式化：默认为"yyyy-MM-dd"
	 * @return
	 * @Description: 判断d1是否小于等于d2
	 * @author yuyanhui
	 */
	public static boolean compareTwoDateLessOrEqual(Date d1, Date d2, String format) {
		return compareDate(d1, d2, format) <= 0;
	}

	/**
	 * 获取现在的系统时间，再加若干天数获取新的时间
	 */
	public static Date addDaysOnCurrentDate(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * 根据传的时间，再加上若干天数获取新的时间
	 */
	public static Date addDaysOnDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static void verifyDateValidity(Date date) {
		if (null != date) {
			if (date.getTime() > getMaxDate().getTime()) {
				throw new IllegalDataException("日期超出有效范围（date ：" + dateTimeToString(date, FORMAT_DATETIME) + "）");
			}
		}
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description:获取下周的第一天
	 * @author yuyanhui
	 */
	public static Date getNextWeekFirstDay() {
		return getNextOrLastWeekFirstDay(true);
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description:获取下周的最后一天
	 * @author yuyanhui
	 */
	public static Date getNextWeekLastDay() {
		return getNextOrLastWeekLastDay(true);
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description:获取上周的第一天
	 * @author yuyanhui
	 */
	public static Date getLastWeekFirstDay() {
		return getNextOrLastWeekFirstDay(false);
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description:获取上周的最后一天
	 * @author yuyanhui
	 */
	public static Date getLastWeekLastDay() {
		return getNextOrLastWeekLastDay(false);
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description:获取下周的第一天
	 * @author yuyanhui
	 */
	public static Date getNextOrLastWeekFirstDay(boolean isNext) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DATE, isNext ? 7 : -7);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Calendar.HOUR_OF_DAY:24小时制
	 * Calendar.HOUR：12小时制
	 *
	 * @return
	 * @Description:获取下周的最后一天
	 * @author yuyanhui
	 */
	public static Date getNextOrLastWeekLastDay(boolean isNext) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.DATE, isNext ? 7 : -7);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 获取当前系统时间
	 *
	 * @return
	 */
	public static Date getSystemDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取系统当前时间字符串
	 *
	 * @return
	 */
	public static String getSystemDateTimeString() {
		Date d = getSystemDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return format.format(d);
	}


	/**
	 * 在一个时间上加上固定月
	 */
	public static Date addMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}


	/**
	 * 计算两个日期差多少天
	 *
	 * @param early 早的日期
	 * @param late  晚的日期
	 * @return
	 */
	public static long diffDays(Date early, Date late) {
		long millisecond = late.getTime() - early.getTime();
		return TimeUnit.MICROSECONDS.toDays(millisecond);
	}

	/**
	 * 计算日期差  间隔类型("Y"--年  "M"--月  "D"--日)
	 *
	 * @param sDate
	 * @param eDate
	 * @param type
	 * @return
	 */
	public static int calInterval(Date sDate, Date eDate, String type) {
		//时间间隔，初始为0
		int interval = 0;
		//比较两个日期的大小，如果开始日期更大，则交换两个日期
		//标志两个日期是否交换过
		boolean reversed = false;
		//将开始时间赋给日历实例
		Calendar sC = Calendar.getInstance();
		sC.setTime(sDate);
		//将结束时间赋给日历实例
		Calendar eC = Calendar.getInstance();
		eC.setTime(eDate);
		//比较
		if (sC.compareTo(eC) > 0) {
			Date dTest = sDate;
			sDate = eDate;
			eDate = dTest;
			//修改交换标志
			reversed = true;
		}
		//将两个日期赋给日历实例，并获取年、月、日相关字段值
		int sYears = sC.get(Calendar.YEAR);
		int sMonths = sC.get(Calendar.MONTH);
		int sDays = sC.get(Calendar.DAY_OF_YEAR);
		int eYears = eC.get(Calendar.YEAR);
		int eMonths = eC.get(Calendar.MONTH);
		int eDays = eC.get(Calendar.DAY_OF_YEAR);
		//年
		if (type.equals("Y")) {
			interval = eYears - sYears;
			if (eMonths < sMonths) {
				--interval;
			}
		}
		//月
		else if (type.equals("M")) {
			interval = 12 * (eYears - sYears);
			interval += (eMonths - sMonths);
		}
		//日
		else if (type.equals("D")) {
			interval = 365 * (eYears - sYears);
			interval += (eDays - sDays);
			//除去闰年天数
			while (sYears < eYears) {
				if (isLeapYear(sYears)) {
					--interval;
				}
				++sYears;
			}
		}
		//如果开始日期更大，则返回负值
		if (reversed) {
			interval = -interval;
		}
		//返回计算结果
		return interval;
	}

	/**
	 * 将秒数转换为分秒，
	 * @param second
	 * @return
	 */
	public static String secondToTime(long second){
		long minutes = second /60;            //转换分钟
		second = second % 60;                //剩余秒数
		return minutes + "分" + second + "秒";

	}


	public static boolean isLeapYear(int year) {
		return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
	}

}
