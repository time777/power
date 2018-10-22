package com.yunshuju.common.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yunshuju.common.utils.MeStringUtils;

/**
 * 日期格式化对象
 */
public class DateFormatThreadLocal extends ThreadLocal<DateFormat>{
	private String format;

	public DateFormatThreadLocal(String format){
		this.format = format;
	}

	@Override
	protected DateFormat initialValue() {
		return new SimpleDateFormat(this.format);
	}

	public String format(Date date){
		if(null == date){
			return null;
		}
		return this.get().format(date);
	}

	public Date parse(String dateStr) throws ParseException {
		if(MeStringUtils.isBlank(dateStr)){
			return null;
		}
		return this.get().parse(dateStr);
	}
}
