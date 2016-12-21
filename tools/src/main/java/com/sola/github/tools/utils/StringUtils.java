package com.sola.github.tools.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by zhangluji
 * 2016/12/19.
 */
public class StringUtils {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String getFormTime(Date date) {
        String retStr;
        long dateTime = date.getTime();
        if (isSameDay(dateTime)) {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);
            int day = calendar.get(Calendar.HOUR_OF_DAY);
            if (day >= 0 && day <= 11) {
                retStr = "HH:mm";
            } else {
                retStr = "HH:mm";
            }
        } else if (isYesterday(dateTime)) {
            retStr = "昨天";
        } else {
            int days = isNDays(dateTime);
            if (days <= 31) {
                return days + "天前";
            } else if (days <= 365) {
                return days / 31 + "月前";
            } else {
                return "1年前";
            }
        }
        return (new SimpleDateFormat(retStr, Locale.CHINA)).format(date);
    }


    private static boolean isSameDay(long dateTime) {
        TimeInfo timeInfo = getTodayStartAndEndTime();
        return dateTime > timeInfo.getStartTime() && dateTime < timeInfo.getEndTime();
    }

    private static boolean isYesterday(long dateTime) {
        TimeInfo timeInfo = getYesterdayStartAndEndTime();
        return dateTime > timeInfo.getStartTime() && dateTime < timeInfo.getEndTime();
    }

    private static int isNDays(long dateTime) {
        TimeInfo timeInfo = getTodayStartAndEndTime();
        return (int) ((-dateTime + timeInfo.getStartTime()) / (24 * 60 * 60 * 1000));
    }

    private static TimeInfo getTodayStartAndEndTime() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        long startTime = startCalendar.getTime().getTime();

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        endCalendar.set(Calendar.MILLISECOND, 999);
        long endTime = endCalendar.getTime().getTime();

        TimeInfo retInfo = new TimeInfo();
        retInfo.setStartTime(startTime);
        retInfo.setEndTime(endTime);
        return retInfo;
    }

    private static TimeInfo getYesterdayStartAndEndTime() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.DAY_OF_MONTH, -1);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        long startTime = startCalendar.getTime().getTime();

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.DAY_OF_MONTH, -1);
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        endCalendar.set(Calendar.MILLISECOND, 999);
        long endTime = endCalendar.getTime().getTime();

        TimeInfo retInfo = new TimeInfo();
        retInfo.setStartTime(startTime);
        retInfo.setEndTime(endTime);
        return retInfo;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
