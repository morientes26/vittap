package com.innovatrics.commons.vittap.pojo;

import java.util.Date;

public class Schedule {
    public NotificationSettings notificationSettings;
    
    public Date startDate;
    public Date endDate;
    
    public ReccurenceType reccurenceType;
    
    // daily
    public Time scheduledTime;
    // weekly
    public DayOfWeek scheduledDay;
    // monthly
    public int scheduledDayOfMonth;
    // yearly
    public Month scheduledMonth;

    // optional : instance_duration (default 1h)
        //time / all_day
    
    // TODO:2017-07-16:mze: create/itterate over Occurances (Occurance factory)
}