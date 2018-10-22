package com.yunshuju.common.utils.date;

/**
 * 日期格式
 */
public final class DateFormats {
    private DateFormats() {
        //
    }

    public static final String STD_DATE = "yyyy-MM-dd";
    public static final String STD_TIME = "HH:mm:ss";
    public static final String STD_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    // 紧凑型 compact
    public static final String CMP_TIMESTAMP = "yyyyMMddHHmmssSSS";
    public static final String CMP_DATE_TIME = "yyyyMMddHHmmss";
    public static final String CMP_DATE = "yyyyMMdd";
    public static final String CMP_TIME = "HHmmss";
    //
    public static final String STD_SHORT_TIME = "HH:mm";
    public static final String STD_SHORT_DATE_TIME = "yyyy-MM-dd HH:mm";
    public static final String STD_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String DATE_DIR_FORMAT = "yyyy/MM/dd";
    public static final String DATE_FILE_FORMAT = "yyyyMMdd";
    public static final String DATE_TIME_FILE_FORMAT = "yyyyMMdd_HHmmss";
    public static final String TIMESTAMP_FILE_FORMAT = "yyyyMMdd_HHmmss_SSS";
}
