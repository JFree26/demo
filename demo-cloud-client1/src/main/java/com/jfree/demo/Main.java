package com.jfree.demo;

import org.springframework.scheduling.support.CronTrigger;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
         DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println(decimalFormat.format(0.159));
    }

    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public static void three(Integer n) {
        int[][] res = new int[n][n];

    }

//    public static List<String> getRecentTriggerTime(String cron) {
//        List<String> list = new ArrayList<String>();
//        try {
//            CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
//            cronTriggerImpl.setCronExpression(cron);
//            // 这个是重点，一行代码搞定
//            List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, 8);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            for (Date date : dates) {
//                list.add(dateFormat.format(date));
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

}
