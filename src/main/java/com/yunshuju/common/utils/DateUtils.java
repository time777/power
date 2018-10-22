package com.yunshuju.common.utils;

import java.util.Date;

/**
 * on 2017/07/12 20:45
 */
public abstract class DateUtils {
    public static Date now() {
        return new Date();
    }

    /***
     * 两个日期相差多少秒
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDateTimeDiffer(Date date1,Date date2){
        //单位是秒
        long timeDelta=(date1.getTime()-date2.getTime())/1000;
        int secondsDelta=timeDelta>0?(int)timeDelta:(int)Math.abs(timeDelta);
        return secondsDelta;
    }
}
