package com.saf.framework;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class PrintKar {

    public static void main(String[] args) {

        String strDate;

        Calendar calendar = Calendar.getInstance();
        System.out.println("Year:" + calendar.get(calendar.YEAR));
        System.out.println("Month: " + (calendar.get(calendar.MONTH) + 1));
        System.out.println("Day: " + calendar.get(calendar.DAY_OF_WEEK));

        System.out.println("Hours: " + calendar.get(calendar.HOUR));
        System.out.println("Minutes: " + calendar.get(calendar.MINUTE));
        System.out.println("Seconds: " + calendar.get(calendar.SECOND));


        String strMonth = ("0" + (calendar.get(calendar.MONTH) + 1));
        if (strMonth.length() > 2) {
            strMonth = strMonth.substring(1, strMonth.length());
        }
        String strDay = ("0" + (calendar.get(calendar.DAY_OF_WEEK)));
        if (strDay.length() > 2) {
            strDay = strDay.substring(1, strDay.length());
        }
        strDate = Integer.toString(calendar.get(calendar.YEAR)) + strMonth + strDay;
        System.out.println("String: " + strDate);

        System.out.println(System.getProperty("user.name"));

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        System.out.println(formatter.format(calendar.getTime()));

    }

}
