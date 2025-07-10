package com.ncode.invoice.pki.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility implements java.io.Serializable {

    public final static String CLASSNAME = "DateUtility";
    public final static String DDMMYYPATTERN = "dd/MM/yyyy";
    public final static String DDMMYYTimePATTERN = "dd/MM/yyyy HH:mm";
    public final static String DDMMYYTimeZonePATTERN = "dd/MM/yyyy HH:mm Z";

    public static String getDateStringFromDate(Date date, String dateFormat) {
        String formattedDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static Date getDateFromString(String dateStr) throws ParseException {
        Date date = null;
        if (dateStr == null || dateStr.trim().length() == 0) {
            return date;
        }
        if (dateStr.trim().length() > 16) {
            date = getDateFromString(dateStr, DDMMYYTimeZonePATTERN);
        } else if (dateStr.trim().length() > 10) {
            date = getDateFromString(dateStr, DDMMYYTimePATTERN);
        } else {
            date = getDateFromString(dateStr, DDMMYYPATTERN);
        }
        return date;
    }

    public static Date getDateFromString(String dateStr, String pattern) throws ParseException {
        Date date = null;
        if (dateStr == null || dateStr.trim().length() == 0) {
            return date;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        date = sdf.parse(dateStr);
        return date;
    }

    public static Long getHoursBtwnDates(Date date1, Date date2) {
        Long hours = null;
        if (date1 == null || date2 == null) {
            return hours;
        }
        long mills1 = 0;
        long mills2 = 0;
        long totalSec = 0;
        long totalMinutes = 0;
        long totalHours = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        mills1 = cal1.getTimeInMillis();
        mills2 = cal2.getTimeInMillis();
        if (mills1 > mills2) {
            totalSec = (mills1 - mills2) / 1000;
        } else {
            totalSec = (mills2 - mills1) / 1000;
        }
        totalMinutes = totalSec / 60;
        totalHours = totalMinutes / 60;
        hours = new Long(totalHours);
        return hours;
    }

    public static long getMinutesBtwnDates(Date date1, Date date2) {
        int hours = 0;
        if (date1 == null || date2 == null) {
            return hours;
        }
        long mills1 = 0;
        long mills2 = 0;
        long totalSec = 0;
        long totalMinutes = 0;
        long totalHours = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        mills1 = cal1.getTimeInMillis();
        mills2 = cal2.getTimeInMillis();
        if (mills1 > mills2) {
            totalSec = (mills1 - mills2) / 1000;
        } else {
            totalSec = (mills2 - mills1) / 1000;
        }
        totalMinutes = totalSec / 60;
        return totalMinutes;
    }

    public static long getSecondsBtwnDates(Date date1, Date date2) {
        long hours = 0;
        if (date1 == null || date2 == null) {
            return hours;
        }
        long mills1 = 0;
        long mills2 = 0;
        long totalSec = 0;
        long totalMinutes = 0;
        long totalHours = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        mills1 = cal1.getTimeInMillis();
        mills2 = cal2.getTimeInMillis();
        if (mills1 > mills2) {
            totalSec = (mills1 - mills2) / 1000;
        } else {
            totalSec = (mills2 - mills1) / 1000;
        }
        hours = (long) (mills2 - mills1) / 1000;
        return hours;
    }

    public static boolean compareDate(Date lowerDate, Date higherDate) {
        boolean isCompared = false;
        if (lowerDate == null || higherDate == null) {
            return isCompared;
        }
        int comparisionVal = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(lowerDate);
        cal2.setTime(higherDate);
        comparisionVal = cal1.compareTo(cal2);
        if (comparisionVal == 0 || comparisionVal > 0) {
            isCompared = false;
        }
        if (comparisionVal < 0) {
            isCompared = true;
        }
        return isCompared;
    }

    public static void main(String[] args) {
        System.out.println(DateUtility.getDateStringFromDate(new Date(), "dd-MMM-yyyy"));
    }
}
