package com.vitta_pilates.model.dao;

import java.util.Date;

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

  private Event(){}

  private Event(final String id,
                final String color,
                final int count,
                final String img,
                final boolean active,
                final Date start,
                final Date end,
                final String title,
                final String textColor,
                final String teacher){
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

  public void setTeacher(String teacher) {
    this.teacher = teacher;
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

    public EventBuilder setEnd(Date start, int duration) {
      java.util.Calendar c = java.util.Calendar.getInstance();
      c.setTime(start);
      c.add(java.util.Calendar.MINUTE, duration);
      this.end =  c.getTime();
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
              this.teacher
      );
    }
  }

}
