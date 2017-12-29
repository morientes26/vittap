package com.innovatrics.commons.vittap.pojo;

public class Time {
    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
    public int hour; // 0..23
    public int minute; // 0..59
}