package com.vitta_pilates.model.dao;

import java.util.Date;

public class Event {

  public static final String GRAY = "#99a3a4";
  public static final String RED = "#fadbd8";
  public static final String BLUE = "#d6eaf8";
  public static final String ORANGE = "#fae5d3";
  public static final String GREEN = "#DAF7A6";
  public static final String PEACH = "#FFC300";

  private String id;
  private String name;
  private String color;
  private int count;
  private String img;
  private boolean active;
  private Date start;
  private Date end;
  private String title;
  private String textColor = "#000";

  public Event(String id){
    this.id = id;
    this.active = false;
  }

  public Event(String id, String name, String color, int count, String img){
    this.id = id;
    this.name = name;
    this.color = color;
    this.count = count;
    this.img = img;
    this.active = true;
  }

  public Event(String id, Date start, Date end, String name, String color, int count){
    this.id = id;
    this.name = name;
    this.color = color;
    this.count = count;
    this.img = img;
    this.active = true;
    this.start = start;
    this.end = end;
    this.title = name;
  }

  public void setId(String id){
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
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
}
