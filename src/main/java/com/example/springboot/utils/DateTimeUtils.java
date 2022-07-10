package com.example.springboot.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Auther: yewub
 * @Date: 2019/5/27 15:53
 * @Description:
 */
public class DateTimeUtils {
    /**
     * yyyy-MM-dd hh:mm:ss date format.
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 按照"yyyy-MM-dd HH:mm:ss" 解析时间
     */
    public static Date parseTime(String dateString) {
        LocalDateTime localDateTime = parseDateTime(dateString, DATE_TIME_FORMAT);
        return fromLocalDateTime(localDateTime);
    }

    /**
     * 按照"yyyy-MM-dd HH:mm:ss" 格式化时间
     */
    public static String formatDateTime(Date date) {
        return formatDateTime(date, DATE_TIME_FORMAT);
    }

    /**
     * 按照指定格式 Date -> String
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDateTime(Date date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return toLocalDateTime(date).format(formatter);
    }

    /**
     * 按照指定格式 时间字符串 -> LocalDateTime
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static LocalDateTime parseDateTime(String dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }


    /**
     * LocalDateTime -> Date
     *
     * @param localDateTime
     * @return
     */
    public static Date fromLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * yyyy-MM-dd字符串格式转成Date
     *
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return fromLocalDateTime(localDate.atStartOfDay());
    }

    /**
     * Date -> LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate asLocalDate(java.util.Date date) {
        return asLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link LocalDate} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
        if (date == null) {
            return null;
        }

        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
        }
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime asLocalDateTime(java.util.Date date) {
        return asLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link LocalDateTime} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static LocalDateTime asLocalDateTime(java.util.Date date, ZoneId zone) {
        if (date == null) {
            return null;
        }

        if (date instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) date).toLocalDateTime();
        } else {
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDateTime();
        }
    }

    /**
     * 任意时间类型的对象 -> java.util.Date
     *
     * @param date
     * @return
     */
    public static java.util.Date asUtilDate(Object date) {
        return asUtilDate(date, ZoneId.systemDefault());
    }

    /**
     * Creates a {@link java.util.Date} from various date objects. Is null-safe. Currently supports:<ul>
     * <li>{@link java.util.Date}
     * <li>{@link java.sql.Date}
     * <li>{@link java.time.LocalDate}
     * <li>{@link java.time.LocalDateTime}
     * <li>{@link java.time.ZonedDateTime}
     * <li>{@link java.time.Instant}
     * </ul>
     *
     * @param zone Time zone, used only if the input object is LocalDate or LocalDateTime.
     * @return {@link java.util.Date} (exactly this class, not a subclass, such as java.sql.Date)
     */
    public static java.util.Date asUtilDate(Object date, ZoneId zone) {
        if (date == null) {
            return null;
        }

        if (date instanceof java.sql.Date || date instanceof java.sql.Timestamp) {
            return new Date(((Date) date).getTime());
        }
        if (date instanceof java.util.Date) {
            return (Date) date;
        }
        if (date instanceof LocalDate) {
            return Date.from(((LocalDate) date).atStartOfDay(zone).toInstant());
        }
        if (date instanceof LocalDateTime) {
            return Date.from(((LocalDateTime) date).atZone(zone).toInstant());
        }
        if (date instanceof ZonedDateTime) {
            return Date.from(((ZonedDateTime) date).toInstant());
        }
        if (date instanceof Instant) {
            return Date.from((Instant) date);
        }

        throw new UnsupportedOperationException("Don't know hot to convert " + date.getClass().getName() + " to java.util.Date");
    }

    /**
     * Date -> ZonedDateTime
     *
     * @param date
     * @return
     */
    public static ZonedDateTime asZonedDateTime(Date date) {
        return asZonedDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Date -> ZonedDateTime
     *
     * @param date
     * @param zone
     * @return
     */
    public static ZonedDateTime asZonedDateTime(Date date, ZoneId zone) {
        if (date == null) {
            return null;
        } else {
            return asInstant(date).atZone(zone);
        }
    }

    /**
     * Date -> Instant
     *
     * @param date
     * @return
     */
    public static Instant asInstant(Date date) {
        if (date == null) {
            return null;
        } else {
            return Instant.ofEpochMilli(date.getTime());
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param day1
     * @param day2
     * @return
     */
    public static long offDaysBetweenDates(Date day1, Date day2) {
        LocalDate localDate1 = asLocalDate(day1);
        LocalDate localDate2 = asLocalDate(day2);
        if (localDate1 == null || localDate2 == null) {
            throw new IllegalArgumentException("params cannot be null");
        }
        if (localDate1.isBefore(localDate2)) {
            return localDate2.toEpochDay() - localDate1.toEpochDay();
        } else {
            return localDate1.toEpochDay() - localDate2.toEpochDay();
        }
    }

    /**
     * 计算两个日期相差的月数
     *
     * @param day1
     * @param day2
     * @return
     */
    public static int offMonthBetweenDates(Date day1, Date day2) {
        LocalDate localDate1 = asLocalDate(day1);
        LocalDate localDate2 = asLocalDate(day2);
        if (localDate1 == null || localDate2 == null) {
            throw new IllegalArgumentException("params cannot be null");
        }
        if (localDate1.isBefore(localDate2)) {
            int diffYear = localDate2.getYear() - localDate1.getYear();
            return diffYear * 12 + (localDate2.getMonthValue() - localDate1.getMonthValue());
        } else {
            int diffYear = localDate1.getYear() - localDate2.getYear();
            return diffYear * 12 + (localDate1.getMonthValue() - localDate2.getMonthValue());
        }
    }

    /**
     * 计算两个日期相差的年数
     *
     * @param day1
     * @param day2
     * @return
     */
    public static int offYearBetweenDates(Date day1, Date day2) {
        LocalDate localDate1 = asLocalDate(day1);
        LocalDate localDate2 = asLocalDate(day2);
        if (localDate1 == null || localDate2 == null) {
            throw new IllegalArgumentException("params cannot be null");
        }
        if (localDate1.isBefore(localDate2)) {
            int diffYear = localDate2.getYear() - localDate1.getYear();
            return diffYear;
        } else {
            int diffYear = localDate1.getYear() - localDate2.getYear();
            return diffYear;
        }
    }


    /**
     * 计算当前日期之后n天的日期
     *
     * @param date
     * @param days
     * @return 如果date为null，返回null。
     */
    public static Date plusDays(Date date, long days) {
        LocalDateTime localDateTime = asLocalDateTime(date);
        if (localDateTime != null) {
            localDateTime = localDateTime.plusDays(days);
            return fromLocalDateTime(localDateTime);
        } else {
            return null;
        }
    }


    /**
     * 计算当前日期之后n天的日期
     *
     * @param date
     * @param months
     * @return 如果date为null，返回null。
     */
    public static Date plusMonths(Date date, long months) {
        LocalDateTime localDateTime = asLocalDateTime(date);
        if (localDateTime != null) {
            localDateTime = localDateTime.plusMonths(months);
            return fromLocalDateTime(localDateTime);
        } else {
            return null;
        }
    }

    /**
     * Instant -> String
     *
     * @param instant
     * @return
     */
    public static String formatInstant(Instant instant) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    /**
     * Instant -> 指定格式的String
     *
     * @param instant
     * @param pattern
     * @return
     */
    public static String formatInstant(Instant instant, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Instant -> LocalDate
     *
     * @param instant
     * @return
     */
    public static LocalDate instantToLocalDate(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 字符串时间 -> Instant
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Instant stringToInstant(String date, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}
