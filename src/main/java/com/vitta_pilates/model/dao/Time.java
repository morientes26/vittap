package com.vitta_pilates.model.dao;

@Deprecated
//NOTE: Don't use this object. It will be better to use LocalTime instead of this
public class Time {
    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
    public int hour; // 0..23
    public int minute; // 0..59
}