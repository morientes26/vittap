package com.vitta_pilates.model.dao;

import com.vitta_pilates.core.event.component.AttendanceForm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

  private String id;
  private String color;
  private int count;
  private String img;
  private boolean active;
  private Date start;
  private Date end;
  private String title;
  private String textColor;
  private String teacher;
  private int duration;
  private String name;
  private String description;
  private String room;

  private AttendanceForm attendanceTeacherForm = new AttendanceForm();
  private List<AttendanceForm> attendanceForm = new ArrayList<>();

  public Event(){}

  public Event(final String id,
                final String color,
                final int count,
                final String img,
                final boolean active,
                final Date start,
                final Date end,
                final String title,
                final String textColor,
                final String teacher,
                final int duration,
                final String name,
                final String description,
                final String room){
    this.id = id;
    this.color = color;
    this.count = count;
    this.img = img;
    this.active = active;
    this.start = start;
    this.end = end;
    this.title = title;
    this.textColor = textColor;
    this.teacher = teacher;
    this.duration = duration;
    this.name = name;
    this.description = description;
    this.room = room;
  }

  public String getId() {
    return id;
  }

  public String getColor() {
    return color;
  }

  public int getCount() {
    return count;
  }

  public String getImg() {
    return img;
  }

  public boolean isActive() {
    return active;
  }

  public Date getStart() {
    return start;
  }

  public Date getEnd() {
    return end;
  }

  public String getTitle() {
    return title;
  }

  public String getTextColor() {
    return textColor;
  }

  public String getTeacher() {
    return teacher;
  }

  public int getDuration() {
    return duration;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public AttendanceForm getAttendanceTeacherForm() {
    return attendanceTeacherForm;
  }

  public void setAttendanceTeacherForm(AttendanceForm attendanceTeacherForm) {
    this.attendanceTeacherForm = attendanceTeacherForm;
  }

  public List<AttendanceForm> getAttendanceForm() {
    return attendanceForm;
  }

  public void setAttendanceForm(List<AttendanceForm> attendanceForm) {
    this.attendanceForm = attendanceForm;
  }

  public static class EventBuilder {
    private String id;
    private String color;
    private int count;
    private String img;
    private boolean active;
    private Date start;
    private Date end;
    private String title;
    private String textColor;
    private String teacher;
    private int duration;
    private String name;
    private String description;
    private String room;

    public EventBuilder(){}

    public EventBuilder setId(String id) {
      this.id = id;
      return this;
    }

    public EventBuilder setId(long id){
      this.id = String.valueOf(id);
      return this;
    }

    public EventBuilder setTitle(String title) {
      this.title = title;
      return this;
    }

    public EventBuilder setColor(String color) {
      this.color = color;
      return this;
    }

    public EventBuilder setCount(int count) {
      this.count = count;
      return this;
    }

    public EventBuilder setImg(String img) {
      //TODO: create a logic of choosing a flag (img src)
      this.img = img;
      return this;
    }

    public EventBuilder setActive(boolean active) {
      this.active = active;
      return this;
    }

    public EventBuilder setStart(Date start) {
      this.start = start;
      return this;
    }

    public EventBuilder setEnd(Date end) {
      this.end = end;
      return this;
    }

    public EventBuilder setTextColor(String textColor) {
      this.textColor = textColor;
      return this;
    }

    public EventBuilder setTeacher(String teacher) {
      this.teacher = teacher;
      return this;
    }

    public EventBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public EventBuilder setDescription(String description) {
      this.description = description;
      return this;
    }

    public EventBuilder setDuration(int duration) {
      this.duration = duration;
      return this;
    }

    public EventBuilder setRoom(String room) {
      this.room = room;
      return this;
    }


    public Event createEvent(){

      return new Event(
              this.id,
              this.color,
              this.count,
              this.img,
              this.active,
              this.start,
              this.end,
              this.title,
              this.textColor,
              this.teacher,
              this.duration,
              this.name,
              this.description,
              this.room
      );
    }
  }

}
