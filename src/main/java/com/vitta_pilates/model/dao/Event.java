package com.vitta_pilates.model.dao;


public class Event {
  private String id;
  private String name;
  private String color;
  private int count;
  private String img;
  private boolean active;

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

}
