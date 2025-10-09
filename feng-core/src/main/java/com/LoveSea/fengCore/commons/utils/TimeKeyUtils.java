package com.LoveSea.fengCore.commons.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;


/**
 * @author xiahaifeng
 */

public class TimeKeyUtils {
    private static final int MINIMAL_DAYS_IN_FIRST_WEEK = 4;

    /**
     * getIsoWeekKey : 获取对应参数日期的iso周制的 weekKey<br>
     *
     * @param localDate 日期
     * @return 对应iso周所在的年份*100+iso周数
     */
    public static int getIsoWeekKey(LocalDate localDate) {
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, MINIMAL_DAYS_IN_FIRST_WEEK);
        int weekOfYear = localDate.get(weekFields.weekOfWeekBasedYear());
        int weekBasedYear = localDate.get(weekFields.weekBasedYear());
        return weekBasedYear * 100 + weekOfYear;
    }

    /**
     * getIsoWeek : 获取对应weekKey的iso周的开始和结束日期<br>
     *
     * @param weekKey iso周制的weekKey
     * @return 对应iso周的开始和结束日期
     */
    public static LocalDate[] getIsoWeekFromKey(int weekKey) {
        int weekOfYear = weekKey % 100;
        int weekBasedYear = weekKey / 100;
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 4);
        LocalDate startDate = LocalDate.of(weekBasedYear, 1, 1)
                .with(weekFields.weekOfWeekBasedYear(), weekOfYear)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDate = startDate.plusDays(6);
        return new LocalDate[]{startDate, endDate};
    }

    /**
     * getDayKey : 获取dayKey<br>
     *
     * @param localDate 日期
     * @return 所在年份*1000+当年第几天
     */
    public static int getDayKey(LocalDate localDate) {
        return localDate.getYear() * 1000 + localDate.getDayOfYear();
    }

    /**
     * getDayFromKey : 获取dayKey对应的日期<br>
     *
     * @param dayKey dayKey
     * @return 所在年份*1000+当年第几天 获取的天数
     */
    public static LocalDate getDayFromKey(int dayKey) {
        int year = dayKey / 1000;
        int dayOfYear = dayKey % 1000;
        return LocalDate.ofYearDay(year, dayOfYear);
    }

    /**
     * getMonthKey :获取monthKey<br>
     *
     * @param localDate 日期
     * @return 所在年份*100+月份（1-12）
     */
    public static int getMonthKey(LocalDate localDate) {
        return localDate.getYear() * 100 + localDate.getMonthValue();
    }

    public static LocalDate[] getMonthFromKey(int monthKey) {
        int year = monthKey / 100;
        int month = monthKey % 100;
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
        return new LocalDate[]{startDate, endDate};
    }
}