package com.jfree.demo.config;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        if (timestamp <= 0) {
            return null;
        }
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static String sqlDateToString(java.sql.Date date) {
        SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
        return st.format(date);
    }

    public static Date localTodate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static LocalDateTime stringToLocalDateTime(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time, df);
    }

    public static String localDateTimeToString(LocalDateTime time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return df.format(time);
    }

    public static LocalDateTime caldendarToLocalDateTime(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return stringToLocalDateTime(df.format(calendar.getTime()));
    }

    public static Date localDateToDate(LocalDate beginDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = beginDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * @param date
     * @return 根据默认时区返回date对象
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        return zonedDateTime.toLocalDate();
    }

    public static LocalDate timestampToLocalDate(@NotNull long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zone);
        String dateStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return LocalDate.parse(dateStr);
    }

    public static LocalDateTime timestampToLocalDateTime(@NotNull long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zone);
        return dateTime;
    }

    public static List<LocalDate> timestampToLocalDate(List<Long> timestampList) {
        if (timestampList == null || timestampList.size() == 0) {
            return null;
        }

        List<LocalDate> dateList = new ArrayList<>();
        for (Long temp : timestampList) {
            LocalDate localDate = DateUtil.timestampToLocalDate(temp);
            dateList.add(localDate);
        }

        return dateList;
    }

    /**
     * 计算两个日期之间的日期
     *
     * @param begin
     * @param end
     * @return
     */
    public static List<Long> calcDate(@NotNull LocalDate begin, @NotNull LocalDate end) {

        List<Long> list = new ArrayList<>();
        final Long day = 24 * 60 * 60 * 1000L;

        LocalDateTime start = LocalDateTime.of(begin.getYear(), begin.getMonth(), begin.getDayOfMonth(), 0, 0, 0);

        LocalDateTime over = LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 0, 0, 0);

        long startDate = DateUtil.localTodate(start).getTime();

        long overDate = DateUtil.localTodate(over).getTime();

        if (startDate > overDate) {
            Long temp = startDate;
            startDate = overDate;
            overDate = temp;
        }

        while (startDate <= overDate) {
            list.add(startDate);

            startDate += day;
        }
        return list;
    }

    public static Calendar stringToCalendar(String str) {
        if (str == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();//log
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        return calendar;
    }

    public static Date stringToDate(String str) {
        if (str == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();//log
        }
        return date;
    }
}