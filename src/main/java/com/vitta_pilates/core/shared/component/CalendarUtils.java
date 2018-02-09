package com.vitta_pilates.core.shared.component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Michal Zelenak
 */
public class CalendarUtils 
{
    public enum DayOfWeek {
        MONDAY(DateTimeConstants.MONDAY),
        TUESDAY(DateTimeConstants.TUESDAY),
        WEDNESDAY(DateTimeConstants.WEDNESDAY),
        THURSDAY(DateTimeConstants.THURSDAY),
        FRIDAY(DateTimeConstants.FRIDAY),
        SATURDAY(DateTimeConstants.SATURDAY),
        SUNDAY(DateTimeConstants.SUNDAY);
     
        public final int calendarCode;
        
        DayOfWeek(int calendarCode) {
            this.calendarCode = calendarCode;
        }
        
        public static DayOfWeek get(int calendarCode) {
            for (DayOfWeek dow : values()) {
                if( dow.calendarCode == calendarCode )
                    return dow;
            }
            
            throw new RuntimeException("Unknow day : " + calendarCode);
        }
    }
    
    static DayOfWeek getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return DayOfWeek.get(c.get(Calendar.DAY_OF_WEEK));
    }
    
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * @param date in dd/MM/yyyy representation
     * @return org.joda.time.DateTime format
     * @throws ParseException 
     */
    public static org.joda.time.DateTime getDateTime(String date) throws ParseException {
        DateTimeFormatter pattern = DateTimeFormat.forPattern("dd/MM/yyyy");
        return pattern.parseDateTime(date);
    }
    
    public static List<DateTime> listDates(DateTime from, DateTime to, int dayOfMonth) {
        if(( dayOfMonth < 1 ) || ( dayOfMonth > 31))
            throw new RuntimeException("Incorrect argument exception : " + dayOfMonth + ", must be between 1 and 31");
        
        List<DateTime> dates = new ArrayList<>();

        DateTime iterator = from;
        while (iterator.isBefore(to) || iterator.isEqual(to)) {
            // controll to limit days (29,30,31)
            int lastDayOfMonth = iterator.dayOfMonth().withMaximumValue().getDayOfMonth();

            if(iterator.getDayOfMonth() == ((dayOfMonth > lastDayOfMonth) ? lastDayOfMonth : dayOfMonth))
                dates.add(iterator);
            
            iterator = iterator.plusDays(1);
        }
        
        return dates;
    }
    
    public static List<DateTime> listDates(DateTime from, DateTime to, DayOfWeek dow) {
        List<DateTime> dates = new ArrayList<>();

        DateTime iterator = from;
        while (iterator.isBefore(to) || iterator.isEqual(to)){
            if(iterator.getDayOfWeek() == dow.calendarCode)
                dates.add(iterator);
            iterator = iterator.plusDays(1);
        }
        
        return dates;
    }
    
    // ---
    
    
    public static void main(String[] args) throws ParseException 
    {
        //list days between two dates
        DateTimeFormatter pattern = DateTimeFormat.forPattern("dd/MM/yyyy");
     
        //for day of month (monthly repetition of an event)
        System.out.println("Days of month");
        DateTime from = getDateTime("01/01/2018");
        DateTime to = getDateTime("01/04/2018");
        List<DateTime> dates = listDates(from, to, from.getDayOfMonth());
        for (DateTime d : dates) {
            System.out.println(d.toString(pattern));
        }

        System.out.println("===============");
        
        //for day of week (weekly repetition of an event)
        System.out.println("Days of week");
        from = getDateTime("01/02/2018");
        to = getDateTime("23/02/2018");
        dates = listDates(from, to, DayOfWeek.get(from.getDayOfWeek()));
        for (DateTime d : dates) {
            System.out.println(d.toString(pattern));
        }
    }
}
