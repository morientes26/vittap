package com.vitta_pilates.core.event.component;

import java.io.Serializable;


public class ActionForm implements Serializable{

  private String attendanceId;
  private String action;
  private Long user;
  private Long user2;
  private boolean isTeacher;
  private boolean fixed;

  public ActionForm(){}

  public String getAttendanceId() {
    return attendanceId;
  }

  public void setAttendanceId(String attendanceId) {
    this.attendanceId = attendanceId;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public Long getUser() {
    return user;
  }

  public void setUser(Long user) {
    this.user = user;
  }

  public boolean isFixed() {
    return fixed;
  }

  public void setFixed(boolean fixed) {
    this.fixed = fixed;
  }

  public boolean isTeacher() {
    return isTeacher;
  }

  public void setTeacher(boolean teacher) {
    isTeacher = teacher;
  }

  public Long getUser2() {
    return user2;
  }

  public void setUser2(Long user2) {
    this.user2 = user2;
  }
}
