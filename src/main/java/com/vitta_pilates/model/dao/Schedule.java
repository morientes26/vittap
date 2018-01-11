package com.vitta_pilates.model.dao;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private NotificationSettings notificationSettings;

    private Date startDate;
    private Date endDate;

    private ReccurenceType reccurenceType;
    
    // daily
    //private Time scheduledTime;
    private LocalTime scheduledTime;
    // weekly
    private DayOfWeek scheduledDay;
    // monthly
    private int scheduledDayOfMonth;
    // yearly
    private Month scheduledMonth;

    // optional : instance_duration (default 1h)
        //time / all_day
    
    // TODO:2017-07-16:mze: create/itterate over Occurances (Occurance factory)


    public Schedule() {
    }

    public long getId() {
        return id;
    }

    public NotificationSettings getNotificationSettings() {
        return notificationSettings;
    }

    public void setNotificationSettings(NotificationSettings notificationSettings) {
        this.notificationSettings = notificationSettings;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ReccurenceType getReccurenceType() {
        return reccurenceType;
    }

    public void setReccurenceType(ReccurenceType reccurenceType) {
        this.reccurenceType = reccurenceType;
    }

    public LocalTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public DayOfWeek getScheduledDay() {
        return scheduledDay;
    }

    public void setScheduledDay(DayOfWeek scheduledDay) {
        this.scheduledDay = scheduledDay;
    }

    public int getScheduledDayOfMonth() {
        return scheduledDayOfMonth;
    }

    public void setScheduledDayOfMonth(int scheduledDayOfMonth) {
        this.scheduledDayOfMonth = scheduledDayOfMonth;
    }

    public Month getScheduledMonth() {
        return scheduledMonth;
    }

    public void setScheduledMonth(Month scheduledMonth) {
        this.scheduledMonth = scheduledMonth;
    }
}