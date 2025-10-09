package com.LoveSea.fengCore.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@Slf4j
public class DateTimeUtils {
    static Calendar calendar = Calendar.getInstance();

    static String[] weekDaysChineseLong = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    static String[] weekDaysChinese = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    static String[] weekDaysEnglish = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static String[] weekDaysChineseShort = {"日", "一", "二", "三", "四", "五", "六"};
    static String[] weekDaysEnglishShort = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    static String[] monthsChinese = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
    static String[] monthsEnglish = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    public static Date getMidnightTime(Date date) {
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static int getWeek(Date date) {
        Calendar cd = Calendar.getInstance();
        // setFirstDayOfWeek的方法意思只对WEEK_OF_MONTH 与WEEK_OF_YEAR 有作用.
        cd.setFirstDayOfWeek(Calendar.MONDAY);
        cd.setMinimalDaysInFirstWeek(4);
        cd.setTime(date);
        return cd.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getYearOfWeek2(Date date) {
        if (null == date) throw new IllegalArgumentException();
        Calendar cd = Calendar.getInstance();
        // 设置一周的第一天是星期一
        cd.setFirstDayOfWeek(Calendar.MONDAY);
        // 设置第一周的最小天数为4
        cd.setMinimalDaysInFirstWeek(4);
        cd.setTime(date);
        int month = cd.get(Calendar.MONTH);
        int weekIndex = cd.get(Calendar.WEEK_OF_YEAR);
        int tempYear = cd.get(Calendar.YEAR);
        if (weekIndex <= 1 && month == 11) {
            // 这一天是第一周，并且是12月，说明这一天所在的周的年份是上一年
            return tempYear + 1;
        }
        if (weekIndex >= 50 && month == 0) {
            // 这一天是最后几周，并且是1月，说明这一天所在的周的年份是下一年
            return tempYear - 1;
        }
        return tempYear;
    }

    public static int getYearOfWeek(Date date) {
        if (null == date) throw new IllegalArgumentException();

        Date startDateOfWeek = getBeginDayOfWeek2(date);
        int sameYearCount = 0;
        int lastYear = getDateYear(startDateOfWeek);
        Date tempDate = startDateOfWeek;
        final int weekDays = 7;
        for (int i = 0; i < weekDays; i++) {
            if (lastYear == getDateYear(tempDate)) {
                sameYearCount++;
            } else {
                lastYear = getDateYear(tempDate);
                sameYearCount = 1;
            }
            if (sameYearCount == 4) {
                return getDateYear(tempDate);
            }
            tempDate = getBackDate(tempDate, 1);
        }
        return getDateYear(tempDate);
    }

    public static Date getBeginDayOfWeek2(Date time) {
        // 转换为Calendar对象
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(time);

        // 设置Calendar为周的第一天
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // 假设周的第一天是星期一
        calendar.setMinimalDaysInFirstWeek(4); // 设置每周最少为4天
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    public static final Date getBackDate(Date date, int day) {
        GregorianCalendar ca = new GregorianCalendar();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH, day);
        return ca.getTime();
    }

    public static int getDateYear(Date date) {
        if (date == null) {
            return 0;
        }
        return getStaticCalendars(date).get(Calendar.YEAR);
    }

    public static Calendar getStaticCalendars(Date date) {
        Calendar staticCal = new GregorianCalendar();
        if (date != null) {
            staticCal.setTime(date);
        }
        return staticCal;
    }

    public static Date getBackDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }


    /**
     * getDiffDate:(求两个日期之间相差的天数(日期相减)). <br/>
     *
     * @author zhiyong.li
     */
    public static int getDiffDate(Date date1, Date date2) {
        return getDiffHour(date1, date2) / 24;
    }

    public static int getDiffHour(Date date1, Date date2) {
        return getDiffMinute(date1, date2) / 60;
    }

    public static int getDiffMinute(Date date1, Date date2) {
        return getDiffSecond(date1, date2) / 60;
    }

    public static int getDiffSecond(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / 1000);
    }
}
