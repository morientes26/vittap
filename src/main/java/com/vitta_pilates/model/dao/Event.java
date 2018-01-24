package com.vitta_pilates.model.dao;

public class Event {

  private String id;
  private String name;
  private String color;
  public  boolean active;

  public Event(String id){
    this.id = id;
    this.active = false;
  }

  public Event(String id, String name, String color){
    this.id = id;
    this.name = name;
    this.color = color;
    this.active = true;
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

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
