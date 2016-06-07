package ua.nure.sentiment.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd");

    public static int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static String weekBefore() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, -7);
        return SIMPLE_DATE_FORMAT.format(c.getTime());
    }

    public static Date dayBefore(int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, -n);
        return c.getTime();
    }

    public static List<String> week() {
        Calendar c = Calendar.getInstance();
        List<String> week = new ArrayList<>();
        for (int i = -7; i <= 0; i++) {
            c.setTime(new Date());
            c.add(Calendar.DAY_OF_MONTH, i);
            week.add(SIMPLE_DATE_FORMAT.format(c.getTime()));
        }
        return week;
    }

    public static String today() {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }


    public static void main(String[] args) {
        System.out.println(weekBefore());
    }

}
