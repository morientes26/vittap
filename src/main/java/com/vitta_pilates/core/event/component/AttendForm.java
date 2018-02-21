package com.vitta_pilates.core.event.component;

import java.io.Serializable;


public class AttendForm implements Serializable{

  private String attendId;
  private String attendanceId;

  public AttendForm(){}

  public String getAttendId() {
    return attendId;
  }

  public void setAttendId(String attendId) {
    this.attendId = attendId;
  }

  public String getAttendanceId() {
    return attendanceId;
  }

  public void setAttendanceId(String attendanceId) {
    this.attendanceId = attendanceId;
  }
}
